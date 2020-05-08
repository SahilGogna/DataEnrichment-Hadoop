package com.bigdata.hadoop.readwriteservices

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

/**
 * @author sahilgogna on 2020-04-18
 */
trait HadoopConfiguration {
  // making a confugration object
  val config = new Configuration()

  // adding configuration files of the cluster to the config object
  config.addResource(new Path("/Users/sahilgogna/opt/hadoop-2.7.3/etc/cloudera/core-site.xml"))
  config.addResource(new Path("/Users/sahilgogna/opt/hadoop-2.7.3/etc/cloudera/hdfs-site.xml"))

  val fileSystem = FileSystem.get(config)
}
