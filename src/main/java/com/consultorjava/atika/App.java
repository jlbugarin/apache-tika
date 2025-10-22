package com.consultorjava.atika;

import org.apache.tika.Tika;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.tika.metadata.Metadata;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
       /*  System.out.println( "#telodijoelbuga");
        Tika tika = new Tika();
        File file = new File("C://Users//jlbug//Downloads//7190386-propuesta_-estrategia-nacional-de-inteligencia-artificial.pdf");
        String texto = tika.parseToString(file);
        System.out.println("Contenido extraído:");
        System.out.println(texto);*/

        Tika tika = new Tika();

        Metadata metadata = new Metadata();
        metadata.set(Metadata.CONTENT_LANGUAGE, "es"); 
        File file = new File("C://Users//jlbug//Downloads//ReciboPE (11).pdf");
        InputStream inputStream = new FileInputStream(file);
        String contenido = tika.parseToString(inputStream,metadata);

        System.out.println("Contenido extraído:");
        System.out.println(contenido);

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("contenido", contenido);

        // Convertir metadatos a JSON
        Map<String, String> metadatosMap = new HashMap<>();
        for (String nombre : metadata.names()) {
            metadatosMap.put(nombre, metadata.get(nombre));
        }
        jsonMap.put("metadatos", metadatosMap);

        // Convertir a JSON con Jackson
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);

        System.out.println(json);


    }
}
