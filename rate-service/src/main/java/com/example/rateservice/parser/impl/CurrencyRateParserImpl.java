package com.example.rateservice.parser.impl;

import com.example.rateservice.dto.CurrencyRateResponse;
import com.example.rateservice.dto.DateCurrencyRateResponse;
import com.example.rateservice.exception.CurrencyRateParsingException;
import com.example.rateservice.parser.CurrencyRateParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CurrencyRateParserImpl implements CurrencyRateParser {

    private final DocumentBuilderFactory factory;

    public CurrencyRateParserImpl() {
        factory = DocumentBuilderFactory.newDefaultInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
    }

    @Override
    public List<CurrencyRateResponse> parse(String ratesAsString) {
        ArrayList<CurrencyRateResponse> rates = new ArrayList<>();

        try {
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var builder = factory.newDocumentBuilder();

            try (var reader = new StringReader(ratesAsString)) {
                Document doc = builder.parse(new InputSource(reader));
                doc.getDocumentElement().normalize();

                NodeList nodes = doc.getElementsByTagName("Valute");

                for (var i = 0; i < nodes.getLength(); i++) {
                    var node = nodes.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        var element = (Element) node;
                        String id = element.getAttribute("ID");
                        String numCode = element.getElementsByTagName("NumCode").item(0).getTextContent();
                        String charCode = element.getElementsByTagName("CharCode").item(0).getTextContent();
                        String nominal = element.getElementsByTagName("Nominal").item(0).getTextContent();
                        String name = element.getElementsByTagName("Name").item(0).getTextContent();
                        String value = element.getElementsByTagName("Value").item(0).getTextContent();
                        rates.add(
                                CurrencyRateResponse.builder()
                                        .id(id)
                                        .numCode(numCode)
                                        .charCode(charCode)
                                        .nominal(Integer.parseInt(nominal))
                                        .name(name)
                                        .value(new BigDecimal(value.replace(",", ".")))
                                        .build()
                        );
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            log.error("XML parsing error: {}, {}", ratesAsString, e.getMessage());
            throw new CurrencyRateParsingException(e);
        }

        return rates;
    }

    @Override
    public List<DateCurrencyRateResponse> parseRange(String rangeAsString) {
        ArrayList<DateCurrencyRateResponse> rates = new ArrayList<>();

        try {
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var builder = factory.newDocumentBuilder();

            try (var reader = new StringReader(rangeAsString)) {
                Document doc = builder.parse(new InputSource(reader));
                doc.getDocumentElement().normalize();

                NodeList nodes = doc.getElementsByTagName("Record");

                for (var i = 0; i < nodes.getLength(); i++) {
                    var node = nodes.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        var element = (Element) node;
                        String id = element.getAttribute("ID");
                        String date = element.getAttribute("Date");
                        String nominal = element.getElementsByTagName("Nominal").item(0).getTextContent();
                        String value = element.getElementsByTagName("Value").item(0).getTextContent();
                        rates.add(
                                DateCurrencyRateResponse.builder()
                                        .id(id)
                                        .nominal(Integer.parseInt(nominal))
                                        .value(new BigDecimal(value.replace(",", ".")))
                                        .build()
                        );
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            log.error("XML parsing error: {}, {}", rangeAsString, e.getMessage());
            throw new CurrencyRateParsingException(e);
        }

        return rates;
    }
}
