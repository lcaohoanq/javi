# Setup

- Include the dependencies

```java
 <dependencies>
    <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.30</version>
      <scope>provided</scope>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core -->
    <dependency>
      <groupId>org.hibernate.orm</groupId>
      <artifactId>hibernate-core</artifactId>
      <version>6.5.2.Final</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/jakarta.persistence/jakarta.persistence-api -->
    <dependency>
      <groupId>jakarta.persistence</groupId>
      <artifactId>jakarta.persistence-api</artifactId>
      <version>3.1.0</version>
    </dependency>
    <!-- H2 Database -->
    <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <version>2.1.214</version>
    </dependency>
  </dependencies>
```

- In case we do not use Spring Boot, must be manually config the build process, which mean let the final product still remain the connection with `persistence.xml` file

```java
<build>
    <resources>
      <resource>
        <directory>src/main/resources</directory>
        <filtering>false</filtering>
        <includes>
          <include>**/persistence.xml</include>
        </includes>
        <targetPath>${project.build.outputDirectory}</targetPath>
      </resource>
    </resources>
    <pluginManagement>
      <plugins>
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
          <executions>
            <execution>
              <id>copy-resources</id>
              <phase>process-resources</phase>
              <goals>
                <goal>copy-resources</goal>
              </goals>
              <configuration>
                <outputDirectory>${project.build.outputDirectory}</outputDirectory>
                <resources>
                  <resource>
                    <directory>${project.basedir}</directory>
                    <includes>
                      <include>.env</include>
                    </includes>
                  </resource>
                </resources>
              </configuration>
            </execution>
          </executions>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
```
