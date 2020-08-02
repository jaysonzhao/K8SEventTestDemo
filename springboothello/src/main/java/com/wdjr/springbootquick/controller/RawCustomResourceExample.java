package com.wdjr.springbootquick.controller;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class RawCustomResourceExample {
  private static final Logger logger = LoggerFactory.getLogger(RawCustomResourceExample.class);

  public static void CreateCR(String test) throws Exception {

    final CountDownLatch closeLatch = new CountDownLatch(1);
    try (final KubernetesClient client = new DefaultKubernetesClient()) {

      String namespace = "default";
      

      // Creating Custom Resources Now:
      CustomResourceDefinitionContext crdContext = new CustomResourceDefinitionContext.Builder()
        .withGroup("sample.javaoperatorsdk")
        .withPlural("webservers")
        .withScope("Namespaced")
        .withVersion("v1")
		.withName(test)
        .build();
      log("Resource created");
      

      
    } catch (KubernetesClientException e) {
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
