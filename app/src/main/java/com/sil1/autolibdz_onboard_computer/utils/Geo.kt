package com.sil1.autolibdz_onboard_computer.utils

class Geo() {

    companion object {
        const val earthRadiusKm: Double = 6372.8
    }

    /**
     * Haversine formula. Giving great-circle distances between two points on a sphere from their longitudes and latitudes.
     * It is a special case of a more general formula in spherical trigonometry, the law of haversines, relating the
     * sides and angles of spherical "triangles".
     *
     * https://rosettacode.org/wiki/Haversine_formula#Java
     *
     * @return Distance in kilometers
     */
    fun haversine(latSrc: Double, lonSrc: Double, latDest: Double, lonDest: Double): Double {
        val dLat = Math.toRadians(latDest - latSrc);
        val dLon = Math.toRadians(lonDest - lonSrc);
        val originLat = Math.toRadians(latSrc);
        val destinationLat = Math.toRadians(latDest);

        val a = Math.pow(Math.sin(dLat / 2), 2.toDouble()) + Math.pow(Math.sin(dLon / 2), 2.toDouble()) * Math.cos(originLat) * Math.cos(destinationLat);
        val c = 2 * Math.asin(Math.sqrt(a));
        return earthRadiusKm * c;
    }

}