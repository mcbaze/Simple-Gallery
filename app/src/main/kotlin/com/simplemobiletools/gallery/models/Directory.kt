package com.simplemobiletools.gallery.models

import com.simplemobiletools.gallery.helpers.SORT_BY_DATE
import com.simplemobiletools.gallery.helpers.SORT_BY_NAME
import com.simplemobiletools.gallery.helpers.SORT_DESCENDING

class Directory(val path: String, val thumbnail: String, val name: String, var mediaCnt: Int, val timestamp: Long, var size: Long) : Comparable<Directory> {
    companion object {
        var sorting: Int = 0
    }

    fun addSize(bytes: Long) {
        size += bytes
    }

    override fun compareTo(other: Directory): Int {
        var res: Int
        if (sorting and SORT_BY_NAME != 0) {
            res = name.toLowerCase().compareTo(other.name.toLowerCase())
        } else if (sorting and SORT_BY_DATE != 0) {
            res = if (timestamp == other.timestamp)
                0
            else if (timestamp > other.timestamp)
                1
            else
                -1
        } else {
            res = if (size == other.size)
                0
            else if (size > other.size)
                1
            else
                -1
        }

        if (sorting and SORT_DESCENDING != 0) {
            res *= -1
        }
        return res
    }

    override fun toString() = "Directory {path=$path, thumbnail=$thumbnail, name=$name, mediaCnt=$mediaCnt, timestamp=$timestamp, size $size}"
}
