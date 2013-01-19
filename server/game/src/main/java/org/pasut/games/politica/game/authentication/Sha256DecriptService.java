package org.pasut.games.politica.game.authentication;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Sha256DecriptService implements DecriptService {
	private static final Logger LOGGER = LoggerFactory.getLogger(Sha256DecriptService.class);
	private final FacebookConfiguration configuration;
	
	@Inject
	public Sha256DecriptService(FacebookConfiguration configuration) {
		this.configuration = configuration;
	}
	
	public String decript(String encryptedSignedRequest) {
		LOGGER.info("Decripting signed request: {}...", encryptedSignedRequest);
		String[] parts = encryptedSignedRequest.split("\\.");
		String encriptedSignature = parts[0];
		String encriptedPayload = parts[1];
		Base64 decoder = new Base64();
		String data = new String(decoder.decode(encriptedPayload));
		validateSignature(encriptedSignature, encriptedPayload, decoder);
		LOGGER.info("Decript succes: {}", data);
		return data;
	}
	
	private void validateSignature(String encriptedSignature,
			String encriptedPayload, Base64 decoder) {
		LOGGER.info("Validating Signature... encriptedSignature: {} - ecripted payload: {}",
				encriptedSignature, encriptedPayload);
		try {
			Mac mac = createShaDecripter();
			byte[] calculatedSignature = mac.doFinal(encriptedPayload.getBytes());
			byte[] signature = decoder.decode(encriptedSignature);
			if (!Arrays.equals(signature, calculatedSignature)) {
				LOGGER.error("Calculated signature was: {} but given signature is: {}",
						calculatedSignature, signature);
				throw new UnableToDecriptException("Calculated signature was: "
						+ Arrays.toString(calculatedSignature) + " but given signature is: "
						+ Arrays.toString(signature));
			}
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Algorithm not found: HMACSHA256", e);
			throw new UnableToDecriptException("Algorithm not found exception : HMACSHA256", e);
		} catch (InvalidKeyException e) {
			LOGGER.error("Key is not valid for decription", e);
			throw new UnableToDecriptException("Key is not valid for decription", e);
		}
	}

	private Mac createShaDecripter() throws NoSuchAlgorithmException,
			InvalidKeyException {
		Mac mac = Mac.getInstance("HMACSHA256");
		mac.init(new SecretKeySpec(configuration.getFacebookSecret().getBytes(), mac.getAlgorithm()));
		return mac;
	}

}
