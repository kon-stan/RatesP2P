import android.util.Log
import android.util.Xml
import com.google.gson.GsonBuilder
import com.konstan.ratesp2p.networking.JsonResponse
import com.konstan.ratesp2p.networking.XmlResponse
import okhttp3.ResponseBody
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.lang.reflect.Type


class MultipleConverterFactory(private val factories: Map<Class<*>, Converter.Factory>) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return annotations.mapNotNull { factories[it.annotationClass.javaObjectType] }
            .getOrNull(0)
            ?.responseBodyConverter(type, annotations, retrofit)
    }

    class Builder {

        private val factories = hashMapOf<Class<*>, Converter.Factory>()

        fun setXmlConverterFactory(converterFactory: Converter.Factory): Builder {
            factories[XmlResponse::class.java] = converterFactory
            return this
        }

        fun setJsonConverterFactory(converterFactory: Converter.Factory): Builder {
            factories[JsonResponse::class.java] = converterFactory
            return this
        }

        @Suppress("unused")
        fun addCustomConverterFactory(
            annotation: Class<out Annotation>,
            converterFactory: Converter.Factory
        ): Builder {
            factories[annotation] = converterFactory
            return this
        }

        fun build(): MultipleConverterFactory {
            return MultipleConverterFactory(factories)
        }
    }
}


internal class XmlOrJsonConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        for (annotation in annotations) {
            if (annotation.annotationClass == XmlResponse::class.java) {
                Log.e("XmlOrJsonConverter", "XML")
                return SimpleXmlConverterFactory.createNonStrict(
                    Persister(AnnotationStrategy())
                ).responseBodyConverter(type, annotations, retrofit)
            }
            if (annotation.annotationClass == JsonResponse::class.java) {
                Log.e("XmlOrJsonConverter", "JsonResponse")
                return GsonConverterFactory.create(
                    GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create()
                ).responseBodyConverter(type, annotations, retrofit)
            }
        }
        return GsonConverterFactory.create(GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create())
            .responseBodyConverter(type, annotations, retrofit)
    }
}