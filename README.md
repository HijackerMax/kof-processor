# KOF-Processor [![GitHub CI Status](https://github.com/hijackermax/kof-processor/workflows/CI/badge.svg)](https://github.com/hijackermax/kof-processor/actions) [![Maven Central](https://img.shields.io/maven-central/v/com.hijackermax/kof-processor)](https://search.maven.org/search?q=g:com.hijackermax%20AND%20a:kof-processor)
Parser for .kof files

Version 0.0.1+ supports only parsing of files and work with 01 and 05 instructions.

### What is kof file?
KOF is an abbreviation for "Kartografi- og Oppmålingsforretningene" in Norwegian, which roughly translates to "mapping and surveying activities". KOF files are typically used in Norway for the exchange of geodetic data and coordinates between various software and hardware systems.

### Prerequisites
* Java 11+

### License
Licensed under the Apache 2.0 License

### Third-Party Software

This project uses the following third-party software:

- [Proj4j](https://github.com/locationtech/proj4j) version 1.2.3 - A Java library for coordinate system transformations. 
Licensed under the [Apache 2.0 License](https://github.com/locationtech/proj4j/blob/master/LICENSE).


### How to add it to your project
Just add this to your **pom.xml**
```xml
<dependency>
    <groupId>com.hijackermax</groupId>
    <artifactId>kof-processor</artifactId>
    <version>0.0.2</version>
</dependency>
```
### Usage:
```java
class Example {
    public void loadCoordinates(InputStream kofStream) throws IOException {
        KOFProcessor kofProcessor = KOFProcessor.readFrom(
                kofStream, 
                CoordinateSystem.EPSG_25832
        );

        List<CoordinatePoint> wsg84Coordinates = kofProcessor.getCoordinates(
                CoordinateSystem.EPSG_4326
        );
    }
}
```