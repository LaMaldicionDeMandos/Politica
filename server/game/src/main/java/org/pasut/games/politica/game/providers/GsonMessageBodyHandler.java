package org.pasut.games.politica.game.providers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GsonMessageBodyHandler implements MessageBodyWriter<Object>,
		MessageBodyReader<Object> {
	private final Gson gson = new Gson(); 
	
	@Override
	public boolean isReadable(Class<?> clazz, Type type, Annotation[] annotations,
			MediaType mediaType) {
		return true;
	}

	@Override
	public Object readFrom(Class<Object> clazz, Type type, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
			InputStream io) throws IOException, WebApplicationException {
		InputStreamReader reader = new InputStreamReader(io, "UTF-8");
		try{
			Type jsonType = clazz.equals(type) ? clazz : type;
			return gson.fromJson(reader, jsonType);
		}finally{
			reader.close();
		}
	}

	@Override
	public long getSize(Object object, Class<?> clazz, Type type,
			Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> clazz, Type type, Annotation[] annotations,
			MediaType mediaType) {
		return true;
	}

	@Override
	public void writeTo(Object object, Class<?> clazz, Type type,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> map, OutputStream out)
			throws IOException, WebApplicationException {
		OutputStreamWriter writer = new OutputStreamWriter(out);
		try{
			Type jsonType = clazz.equals(type) ? clazz : type;
			gson.toJson(object, jsonType, writer);
		}finally{
			writer.close();
		}
	}

}
