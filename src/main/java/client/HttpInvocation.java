//package client;
//
//import org.glassfish.jersey.client.ClientConfig;
//import org.glassfish.jersey.client.JerseyInvocation;
//
//import javax.ws.rs.client.*;
//import javax.ws.rs.core.*;
//import java.net.URI;
//import java.util.Locale;
//import java.util.concurrent.Future;
//
//public class HttpInvocation implements Invocation {
//
//    public static class Builder implements Invocation.Builder{
//
//        private final Invocation.Builder builder;
//
//        public Builder() {
//            this.builder = new JerseyInvocation.Builder();
//        }
//
//        @Override
//        public Invocation build(String method) {
//            return null;
//        }
//
//        @Override
//        public Invocation build(String method, Entity<?> entity) {
//            return null;
//        }
//
//        @Override
//        public Invocation buildGet() {
//            return null;
//        }
//
//        @Override
//        public Invocation buildDelete() {
//            return null;
//        }
//
//        @Override
//        public Invocation buildPost(Entity<?> entity) {
//            return null;
//        }
//
//        @Override
//        public Invocation buildPut(Entity<?> entity) {
//            return null;
//        }
//
//        @Override
//        public AsyncInvoker async() {
//            return null;
//        }
//
//        @Override
//        public Invocation.Builder accept(String... mediaTypes) {
//            return null;
//        }
//
//        @Override
//        public Invocation.Builder accept(MediaType... mediaTypes) {
//            return null;
//        }
//
//        @Override
//        public Invocation.Builder acceptLanguage(Locale... locales) {
//            return null;
//        }
//
//        @Override
//        public Invocation.Builder acceptLanguage(String... locales) {
//            return null;
//        }
//
//        @Override
//        public Invocation.Builder acceptEncoding(String... encodings) {
//            return null;
//        }
//
//        @Override
//        public Invocation.Builder cookie(Cookie cookie) {
//            return null;
//        }
//
//        @Override
//        public Invocation.Builder cookie(String name, String value) {
//            return null;
//        }
//
//        @Override
//        public Invocation.Builder cacheControl(CacheControl cacheControl) {
//            return null;
//        }
//
//        @Override
//        public Invocation.Builder header(String name, Object value) {
//            return null;
//        }
//
//        @Override
//        public Invocation.Builder headers(MultivaluedMap<String, Object> headers) {
//            return null;
//        }
//
//        @Override
//        public Invocation.Builder property(String name, Object value) {
//            return null;
//        }
//
//        @Override
//        public CompletionStageRxInvoker rx() {
//            return null;
//        }
//
//        @Override
//        public <T extends RxInvoker> T rx(Class<T> clazz) {
//            return null;
//        }
//
//        @Override
//        public Response get() {
//            return null;
//        }
//
//        @Override
//        public <T> T get(Class<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public <T> T get(GenericType<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public Response put(Entity<?> entity) {
//            return null;
//        }
//
//        @Override
//        public <T> T put(Entity<?> entity, Class<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public <T> T put(Entity<?> entity, GenericType<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public Response post(Entity<?> entity) {
//            return null;
//        }
//
//        @Override
//        public <T> T post(Entity<?> entity, Class<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public <T> T post(Entity<?> entity, GenericType<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public Response delete() {
//            return null;
//        }
//
//        @Override
//        public <T> T delete(Class<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public <T> T delete(GenericType<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public Response head() {
//            return null;
//        }
//
//        @Override
//        public Response options() {
//            return null;
//        }
//
//        @Override
//        public <T> T options(Class<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public <T> T options(GenericType<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public Response trace() {
//            return null;
//        }
//
//        @Override
//        public <T> T trace(Class<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public <T> T trace(GenericType<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public Response method(String name) {
//            return null;
//        }
//
//        @Override
//        public <T> T method(String name, Class<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public <T> T method(String name, GenericType<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public Response method(String name, Entity<?> entity) {
//            return null;
//        }
//
//        @Override
//        public <T> T method(String name, Entity<?> entity, Class<T> responseType) {
//            return null;
//        }
//
//        @Override
//        public <T> T method(String name, Entity<?> entity, GenericType<T> responseType) {
//            return null;
//        }
//    }
//
//    @Override
//    public Invocation property(String name, Object value) {
//        return null;
//    }
//
//    @Override
//    public Response invoke() {
//        return null;
//    }
//
//    @Override
//    public <T> T invoke(Class<T> responseType) {
//        return null;
//    }
//
//    @Override
//    public <T> T invoke(GenericType<T> responseType) {
//        return null;
//    }
//
//    @Override
//    public Future<Response> submit() {
//        return null;
//    }
//
//    @Override
//    public <T> Future<T> submit(Class<T> responseType) {
//        return null;
//    }
//
//    @Override
//    public <T> Future<T> submit(GenericType<T> responseType) {
//        return null;
//    }
//
//    @Override
//    public <T> Future<T> submit(InvocationCallback<T> callback) {
//        return null;
//    }
//}
