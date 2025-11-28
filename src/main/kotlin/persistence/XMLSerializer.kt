package ie.setu.persistence

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.DomDriver
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class XMLSerializer(private val file: File) : Serializer {

    @Throws(Exception::class)
    override fun write(obj: Any?) {
        val xStream = XStream(DomDriver())
        xStream.allowTypesByWildcard(arrayOf("ie.setu.models.*"))
        val outputStream = xStream.createObjectOutputStream(FileWriter(file))
        outputStream.writeObject(obj)
        outputStream.close()
    }

    @Throws(Exception::class)
    override fun read(): Any? {
        if (!file.exists() || file.readText().isEmpty()) return null
        val xStream = XStream(DomDriver())
        xStream.allowTypesByWildcard(arrayOf("ie.setu.models.*"))
        val inputStream = xStream.createObjectInputStream(FileReader(file))
        val obj = inputStream.readObject()
        inputStream.close()
        return obj
    }
}
