val junitJupiterVersion: String by project
val jdbiVersion: String by project

plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "ru.pranch"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jdbi:jdbi3-spring5:$jdbiVersion")
    implementation("org.jdbi:jdbi3-sqlobject:$jdbiVersion")
    implementation("org.jdbi:jdbi3-postgres:$jdbiVersion")

    implementation("org.flywaydb:flyway-core")

    runtimeOnly ("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}