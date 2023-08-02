package client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.SyncInvoker;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.concurrent.Future;

public class HttpInvocation implements Invocation {
    @Override
    public Invocation property(String name, Object value) {
        return null;
    }

    @Override
    public Response invoke() {
        return null;
    }

    @Override
    public <T> T invoke(Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T invoke(GenericType<T> responseType) {
        return null;
    }

    @Override
    public Future<Response> submit() {
        return null;
    }

    @Override
    public <T> Future<T> submit(Class<T> responseType) {
        return null;
    }

    @Override
    public <T> Future<T> submit(GenericType<T> responseType) {
        return null;
    }

    @Override
    public <T> Future<T> submit(InvocationCallback<T> callback) {
        return null;
    }

//    public static class Builder implements SyncInvoker {
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

}
