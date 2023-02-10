plugins {
  id("org.springframework.boot") version "2.2.11.RELEASE"
  id("io.spring.dependency-management") version "1.0.12.RELEASE"
  id("java")
  id("com.google.cloud.tools.jib") version "3.3.0"
}

group = "dev.bigwig"
version = "0.1.0"
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

val developmentOnly = configurations.create("developmentOnly")
configurations {
  developmentOnly
  runtimeClasspath {
    extendsFrom(developmentOnly)
  }
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
}

repositories {
  mavenCentral()
}

val springCloudAlibabaVersion = "2.2.8.RELEASE"
val mapstructVersion = "1.5.2.Final"
val knife4jVersion = "3.0.3"
val hutoolVersion = "5.8.12"
val zxingVersion = "3.5.0"
val jjwtVersion = "0.9.1"
val poiVersion = "5.2.2"
val minioVersion = "8.4.3"
val jetbrainsAnnotationVersion = "23.0.0"
val retrofitVersion = "2.3.6"

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-web") {
    exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
  }
  implementation("org.springframework.boot:spring-boot-starter-undertow")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5")
  implementation("org.apache.commons:commons-pool2")
  implementation("javax.validation:validation-api")
  implementation("com.github.xiaoymin:knife4j-spring-boot-starter:${knife4jVersion}")
  implementation("cn.hutool:hutool-all:${hutoolVersion}")
  implementation("com.google.zxing:core:${zxingVersion}")
  implementation("io.jsonwebtoken:jjwt:${jjwtVersion}")
  implementation("org.apache.poi:poi-ooxml:${poiVersion}")
  implementation("io.minio:minio:${minioVersion}")
  implementation("com.github.lianjiatech:retrofit-spring-boot-starter:${retrofitVersion}")
  implementation("com.google.guava:guava")
  compileOnly("org.projectlombok:lombok")
  compileOnly("org.mapstruct:mapstruct:${mapstructVersion}")
  compileOnly("org.jetbrains:annotations:${jetbrainsAnnotationVersion}")
  developmentOnly("org.springframework.boot:spring-boot-devtools")
  runtimeOnly("com.h2database:h2")
  runtimeOnly("mysql:mysql-connector-java")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
  annotationProcessor("org.projectlombok:lombok")
  annotationProcessor("org.mapstruct:mapstruct-processor:${mapstructVersion}")
  annotationProcessor("org.jetbrains:annotations:${jetbrainsAnnotationVersion}")
  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }
  testImplementation("org.springframework.security:spring-security-test")
}

dependencyManagement {
  imports {
    mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:${springCloudAlibabaVersion}")
  }
}

tasks {
  withType<Test> {
    useJUnitPlatform()
    testLogging {
      showExceptions = true
      showStandardStreams = true
    }
  }

  withType<Wrapper> {
    gradleVersion = gradleVersion
    distributionType = Wrapper.DistributionType.BIN
  }

  withType<JavaCompile> {
    options.isFork = true
  }

  build {
    dependsOn("jib")
  }
}

springBoot {
  buildInfo()
}

jib {
  from {
    image = "adoptopenjdk:11-jre-hotspot"
  }

  to {
    image = "m01i0ng/bigwig-fast-rest"
    tags = setOf("latest", "$version")
  }

  container {
    expandClasspathDependencies = true
    jvmFlags = listOf("-Xms512m", "-Xmx512m")
    ports = listOf("8080")
    creationTime = "USE_CURRENT_TIMESTAMP"
    environment = mapOf("TZ" to "Asia/Shanghai")
  }
}
