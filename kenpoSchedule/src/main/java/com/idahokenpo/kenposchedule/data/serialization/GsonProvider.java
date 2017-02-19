package com.idahokenpo.kenposchedule.data.serialization;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Korey
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GsonProvider<WeeklySchedule> implements MessageBodyReader<WeeklySchedule>, MessageBodyWriter<WeeklySchedule>
{
    private final Gson gson;

    @Context
    private UriInfo ui;

    public GsonProvider()
    {
        this.gson = SerializationUtils.getGson();
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType)
    {
        return true;
    }

    @Override
    public WeeklySchedule readFrom(Class<WeeklySchedule> type, Type genericType, Annotation[] annotations,
            MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream) throws IOException, WebApplicationException
    {

        InputStreamReader reader = new InputStreamReader(entityStream, "UTF-8");
        try
        {
            return gson.fromJson(reader, type);
        } finally
        {
            reader.close();
        }
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType)
    {
        return true;
    }

    @Override
    public long getSize(WeeklySchedule t, Class<?> type, Type genericType,
            Annotation[] annotations, MediaType mediaType)
    {
        return -1;
    }

    @Override
    public void writeTo(WeeklySchedule t, Class<?> type, Type genericType, Annotation[] annotations,
            MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) throws IOException, WebApplicationException
    {

        PrintWriter printWriter = new PrintWriter(entityStream);
        try
        {
            String json = gson.toJson(t);
            
            printWriter.write(json);
            printWriter.flush();
        } finally
        {
            printWriter.close();
        }
    }

}
