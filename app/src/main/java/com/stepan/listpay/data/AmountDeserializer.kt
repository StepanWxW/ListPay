package com.stepan.listpay.data

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.stepan.listpay.domain.model.Amount
import java.lang.reflect.Type
import java.math.BigDecimal

class AmountDeserializer : JsonDeserializer<Amount> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Amount {
        return if (json?.isJsonPrimitive == true) {
            val primitive = json.asJsonPrimitive
            if (primitive.isNumber) {
                Amount.Value(BigDecimal(primitive.asString))
            } else if (primitive.isString) {
                if (primitive.asString.isEmpty()) {
                    Amount.Empty(primitive.asString)
                } else {
                    Amount.Value(BigDecimal(primitive.asString))
                }
            } else {
                throw JsonParseException("Unexpected JSON type: ${primitive.javaClass}")
            }
        } else {
            throw JsonParseException("Unexpected JSON structure: ${json?.javaClass}")
        }
    }
}