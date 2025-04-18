plugins {
    id("org.openjfx.javafxplugin") version "0.1.0"
}

javafx {
    modules("javafx.controls", "javafx.fxml")
    configuration = "implementation"
}

dependencies {
    implementation("org.openjfx:javafx-controls:16")
    implementation("org.openjfx:javafx-fxml:16")
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}