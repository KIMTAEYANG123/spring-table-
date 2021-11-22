package hacs.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import hacs.mvc.domain.BaseCodeLabelEnum;





public class BaseCodeLabelEnumJsonSerializer extends JsonSerializer<BaseCodeLabelEnum>{

	@Override
	public void serialize(BaseCodeLabelEnum value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		Map<String,String> map = new HashMap<>();
		map.put("code", value.code());
		map.put("label", value.label());
		gen.writeObject(map);
	}

}