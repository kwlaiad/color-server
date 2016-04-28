package ken.task;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import ken.task.Exception.ServerException;
import ken.task.Exception.ServerShutdownException;
import ken.task.Exception.UriNotFoundException;
import ken.task.Util.ErrorResponseUtil;
import ken.task.model.ResponseError;
import org.apache.http.*;
import org.apache.http.impl.nio.bootstrap.HttpServer;
import org.apache.http.impl.nio.bootstrap.ServerBootstrap;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.protocol.*;
import org.apache.http.protocol.HttpContext;

/**
 * Embedded HTTP/1.1 file server based on a non-blocking I/O model and capable of direct channel
 * (zero copy) data transfer.
 */
public class Server {

    private static HttpServer server;
    public static void main(String[] args) throws Exception {

        int port = 4000;
//        if (args.length >= 2) {
//            port = Integer.parseInt(args[0]);
//        }
//
//        SSLContext sslcontext = null;
//        if (port == 8443) {
//            // Initialize SSL context
//            URL url = NApiServer.class.getResource("/my.keystore");
//            if (url == null) {
//                System.out.println("Keystore not found");
//                System.exit(1);
//            }
//            sslcontext = SSLContexts.custom()
//                    .loadKeyMaterial(url, "secret".toCharArray(), "secret".toCharArray())
//                    .build();
//        }

        IOReactorConfig config = IOReactorConfig.custom()
                .setSoTimeout(15000)
                .setTcpNoDelay(true)
                .build();

        server = ServerBootstrap.bootstrap()
                .setListenerPort(port)
                .setServerInfo("TEST/1.1")
                .setIOReactorConfig(config)
//                .setSslContext(sslcontext)
                .setExceptionLogger(ExceptionLogger.STD_ERR)
                .registerHandler("*", new HttpFileHandler())
                .create();

        server.start();
        server.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                server.shutdown(5, TimeUnit.SECONDS);
            }
        });

    }

    public static void serverShutdown(){
        server.shutdown(5, TimeUnit.SECONDS);
    }

    static class HttpFileHandler implements HttpAsyncRequestHandler<HttpRequest> {

        public HttpFileHandler() {
            super();
        }

        public HttpAsyncRequestConsumer<HttpRequest> processRequest(
                final HttpRequest request,
                final HttpContext context) {
            // Buffer request content in memory for simplicity
            return new BasicAsyncRequestConsumer();
        }

        public void handle(
                final HttpRequest request,
                final HttpAsyncExchange httpexchange,
                final HttpContext context) throws HttpException, IOException {
            HttpResponse response = httpexchange.getResponse();
            try {
                handleInternal(request, response, context);
            } catch (UriNotFoundException e) {
                e.printStackTrace();
            }
            httpexchange.submitResponse(new BasicAsyncResponseProducer(response));
        }

        private void handleInternal(
                final HttpRequest request,
                final HttpResponse response,
                final HttpContext context) throws HttpException, IOException, UriNotFoundException {

            try {
                Route.route(request, response, context);
            } catch (ServerShutdownException e){
                System.out.println("Received shutdown signal");
                e.handleResponseToClientForError(response);
                new Thread(() -> {
                    try {
                        Thread.sleep(10000);
                    } catch(InterruptedException ex) {
                    }
                    System.out.println("Now shutdown...");
                    e.shutdown(server);
                }).start();

            } catch (ServerException e) {

                e.handleResponseToClientForError(response); //Handle to client error response

            } catch (MethodNotSupportedException e){
                ResponseError errorResponse = ErrorResponseUtil.errorBuilder(null, HttpStatus.SC_METHOD_NOT_ALLOWED, "method not found");
                ErrorResponseUtil.writeJson(errorResponse, response);
            }
        }
    }
}
