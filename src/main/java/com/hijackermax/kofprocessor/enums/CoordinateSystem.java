package com.hijackermax.kofprocessor.enums;

import java.util.stream.Stream;

/**
 * Enum describing different coordinate systems and provides configuration for their transformation
 */
public enum CoordinateSystem {
    EPSG_23032(23032, 32, "EPSG:23032", "+proj=utm +zone=32 +ellps=intl +towgs84=-87,-98,-121,0,0,0,0 +units=m +no_defs"),
    EPSG_25830(25830, 20, "EPSG:25830", "+proj=utm +zone=30 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_25831(25831, 21, "EPSG:25831", "+proj=utm +zone=31 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_25832(25832, 22, "EPSG:25832", "+proj=utm +zone=32 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_25833(25833, 23, "EPSG:25833", "+proj=utm +zone=33 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_25834(25834, 24, "EPSG:25834", "+proj=utm +zone=34 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_25835(25835, 25, "EPSG:25835", "+proj=utm +zone=35 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_25836(25836, 26, "EPSG:25836", "+proj=utm +zone=36 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_3857(3857, 300, "EPSG:3857", "+proj=merc +a=6378137 +b=6378137 +lat_ts=0.0 +lon_0=0.0 +x_0=0.0 +y_0=0 +k=1.0 +units=m +nadgrids=@null +wktext  +no_defs"),
    EPSG_4326(4326, 84, "EPSG:4326", "+proj=longlat +datum=WGS84 +no_defs"),
    EPSG_5105(5105, 205, "EPSG:5105", "+proj=tmerc +lat_0=58 +lon_0=5.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5106(5106, 206, "EPSG:5106", "+proj=tmerc +lat_0=58 +lon_0=6.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5107(5107, 207, "EPSG:5107", "+proj=tmerc +lat_0=58 +lon_0=7.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5108(5108, 208, "EPSG:5108", "+proj=tmerc +lat_0=58 +lon_0=8.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5109(5109, 209, "EPSG:5109", "+proj=tmerc +lat_0=58 +lon_0=9.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5110(5110, 210, "EPSG:5110", "+proj=tmerc +lat_0=58 +lon_0=10.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5111(5111, 211, "EPSG:5111", "+proj=tmerc +lat_0=58 +lon_0=11.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5112(5112, 212, "EPSG:5112", "+proj=tmerc +lat_0=58 +lon_0=12.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5113(5113, 213, "EPSG:5113", "+proj=tmerc +lat_0=58 +lon_0=13.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5114(5114, 214, "EPSG:5114", "+proj=tmerc +lat_0=58 +lon_0=14.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5115(5115, 215, "EPSG:5115", "+proj=tmerc +lat_0=58 +lon_0=15.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5116(5116, 216, "EPSG:5116", "+proj=tmerc +lat_0=58 +lon_0=16.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5117(5117, 217, "EPSG:5117", "+proj=tmerc +lat_0=58 +lon_0=17.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5118(5118, 218, "EPSG:5118", "+proj=tmerc +lat_0=58 +lon_0=18.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5119(5119, 219, "EPSG:5119", "+proj=tmerc +lat_0=58 +lon_0=19.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5120(5120, 220, "EPSG:5120", "+proj=tmerc +lat_0=58 +lon_0=20.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5121(5121, 221, "EPSG:5121", "+proj=tmerc +lat_0=58 +lon_0=21.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5122(5122, 222, "EPSG:5122", "+proj=tmerc +lat_0=58 +lon_0=22.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5123(5123, 223, "EPSG:5123", "+proj=tmerc +lat_0=58 +lon_0=23.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5124(5124, 224, "EPSG:5124", "+proj=tmerc +lat_0=58 +lon_0=24.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5125(5125, 225, "EPSG:5125", "+proj=tmerc +lat_0=58 +lon_0=25.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5126(5126, 226, "EPSG:5126", "+proj=tmerc +lat_0=58 +lon_0=26.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5127(5127, 227, "EPSG:5127", "+proj=tmerc +lat_0=58 +lon_0=27.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5128(5128, 228, "EPSG:5128", "+proj=tmerc +lat_0=58 +lon_0=28.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5129(5129, 229, "EPSG:5129", "+proj=tmerc +lat_0=58 +lon_0=29.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs"),
    EPSG_5130(5130, 230, "EPSG:5130", "+proj=tmerc +lat_0=58 +lon_0=30.5 +k=1 +x_0=100000 +y_0=1000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs");

    private final int id;
    private final int referenceId;
    private final String name;
    private final String data;

    CoordinateSystem(int id, int referenceId, String name, String data) {
        this.id = id;
        this.referenceId = referenceId;
        this.name = name;
        this.data = data;
    }

    /**
     * Conducts search of {@link CoordinateSystem} by provided reference id
     *
     * @param id target reference id
     * @return {@link CoordinateSystem} that matches provided reference id,
     * or throws {@link IllegalArgumentException} if reference id is unknown
     */
    public static CoordinateSystem findByReferenceId(int id) {
        return Stream.of(values())
                .filter(type -> type.referenceId == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unknown reference id = %d", id)));
    }

    /**
     * Conducts search of {@link CoordinateSystem} by provided EPSG id
     *
     * @param id target EPSG id
     * @return {@link CoordinateSystem} that matches provided EPSG id,
     * or throws {@link IllegalArgumentException} if EPSG id is unknown
     */
    public static CoordinateSystem findByEPSGId(int id) {
        return Stream.of(values())
                .filter(type -> type.id == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unknown EPSG id = %d", id)));
    }

    /**
     * Returns EPSG id
     *
     * @return EPSG id of instance of {@link CoordinateSystem}
     */
    public int getId() {
        return id;
    }

    /**
     * Returns reference id
     *
     * @return reference id in "Coordinated Approach for Spatial Information" of instance of {@link CoordinateSystem}
     */
    public int getReferenceId() {
        return referenceId;
    }

    /**
     * Returns name
     *
     * @return name of coordinate system
     */
    public String getName() {
        return name;
    }

    /**
     * Returns configuration line for coordinate transformation
     *
     * @return configuration for coordinate transformation of instance of {@link CoordinateSystem}
     */
    public String getData() {
        return data;
    }
}
