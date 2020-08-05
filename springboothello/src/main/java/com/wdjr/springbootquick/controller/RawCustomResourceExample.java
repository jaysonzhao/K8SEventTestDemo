package com.wdjr.springbootquick.controller;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class RawCustomResourceExample {
  private static final Logger logger = LoggerFactory.getLogger(RawCustomResourceExample.class);

  public static void CreateCR(String test) {

    final CountDownLatch closeLatch = new CountDownLatch(1);
    try (final KubernetesClient client = new DefaultKubernetesClient()) {

      String namespace = "webserver-operator";
      

      // Creating Custom Resources Now:
      CustomResourceDefinitionContext crdContext = new CustomResourceDefinitionContext.Builder()
        .withGroup("sample.javaoperatorsdk")
        .withPlural("webservers")
        .withScope("Namespaced")
        .withVersion("v1")
        .build();

      // Creating from HashMap
            Map<String, Object> cr1 = client
                    .customResource(crdContext)
                    .load(RawCustomResourceExample.class.getResourceAsStream("/web-cr.yaml"));
            //client.customResource(animalCrdContext).create(namespace, cr1);

            // Creating from JSON String
            JSONObject cr2Json = new JSONObject(cr1);
            cr2Json.getJSONObject("metadata").put("name", test);
            cr2Json.getJSONObject("spec").put("html", "hello from springboot app");

            client.customResource(crdContext).create(namespace, cr2Json.toString());
      log("Resource created");
      // Listing all custom resources in given namespace:
      Map<String, Object> list = client.customResource(crdContext).list(namespace);
      List<Map<String, Object>> items = (List<Map<String, Object>>) list.get("items");
      log("Custom Resources :- ");
      for(Map<String, Object> customResource : items) {
        Map<String, Object> metadata = (Map<String, Object>) customResource.get("metadata");
        log(metadata.get("name").toString());
      }


      
    } catch (KubernetesClientException e) {
      e.printStackTrace();
      log("Could not create resource", e.getMessage());
    } catch (IOException e){
      e.printStackTrace();
      log("Could not create resource", e.getMessage());


   }
  }

  private static void log(String action, Object obj) {
    logger.info("{}: {}", action, obj);
  }

  private static void log(String action) {
    logger.info(action);
  }
}
