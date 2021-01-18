/*
 * **************************************************-
 * ingrid-utils
 * ==================================================
 * Copyright (C) 2014 - 2021 wemove digital solutions GmbH
 * ==================================================
 * Licensed under the EUPL, Version 1.1 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 * 
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 * 
 * http://ec.europa.eu/idabc/eupl5
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * **************************************************#
 */
package de.ingrid.utils.metadata;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.ingrid.utils.IBus;
import de.ingrid.utils.IngridHit;
import de.ingrid.utils.IngridHits;
import de.ingrid.utils.query.IngridQuery;
import de.ingrid.utils.queryparser.QueryStringParser;

public abstract class AbstractIPlugOperatorInjector implements
		IMetadataInjector, IBusable {

	public static final String IPLUG_OPERATOR = "IPLUG_OPERATOR";

	private Log LOG = LogFactory.getLog(AbstractIPlugOperatorInjector.class);

	private IBus _bus;

	public static class IPlugOperator implements Externalizable {

		private static final long serialVersionUID = 8719569972941213577L;

		private Map<String, Partner> _partners = new HashMap<String, Partner>();

		public List<Partner> getPartners() {
			return new ArrayList<Partner>(_partners.values());
		}

		public void addPartner(Partner partner) {
			_partners.put(partner.getShortName(), partner);
		}

		public Partner getPartner(String partnerId) {
			return _partners.get(partnerId);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((_partners == null) ? 0 : _partners.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			IPlugOperator other = (IPlugOperator) obj;
			if (_partners == null) {
				if (other._partners != null)
					return false;
			} else if (!_partners.equals(other._partners))
				return false;
			return true;
		}

		@Override
		public String toString() {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(System.getProperty("line.separator"));
			for (Partner partner : getPartners()) {
				stringBuffer.append(partner);
			}
			return stringBuffer.toString();
		}

		@SuppressWarnings("unchecked")
        @Override
		public void readExternal(ObjectInput in) throws IOException,
				ClassNotFoundException {
			_partners = (Map<String, Partner>) in.readObject();
		}

		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeObject(_partners);
		}

	}

	public static class Partner implements Externalizable {

		private static final long serialVersionUID = -7342292962488266521L;

		private String _shortName;

		private String _displayName;

		private Map<String, Provider> _providers = new HashMap<String, Provider>();

		public String getShortName() {
			return _shortName;
		}

		public void setShortName(String shortName) {
			_shortName = shortName;
		}

		public String getDisplayName() {
			return _displayName;
		}

		public void setDisplayName(String displayName) {
			_displayName = displayName;
		}

		public List<Provider> getProviders() {
			return new ArrayList<Provider>(_providers.values());
		}

		public void addProvider(Provider provider) {
			_providers.put(provider.getShortName(), provider);
		}

		public Provider getProvider(String providerId) {
			return _providers.get(providerId);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((_displayName == null) ? 0 : _displayName.hashCode());
			result = prime * result
					+ ((_providers == null) ? 0 : _providers.hashCode());
			result = prime * result
					+ ((_shortName == null) ? 0 : _shortName.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Partner other = (Partner) obj;
			if (_displayName == null) {
				if (other._displayName != null)
					return false;
			} else if (!_displayName.equals(other._displayName))
				return false;
			if (_providers == null) {
				if (other._providers != null)
					return false;
			} else if (!_providers.equals(other._providers))
				return false;
			if (_shortName == null) {
				if (other._shortName != null)
					return false;
			} else if (!_shortName.equals(other._shortName))
				return false;
			return true;
		}

		@Override
		public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("Partner: [" + _shortName + "/" + _displayName + "]");
			for (Provider provider : getProviders()) {
				buffer.append(System.getProperty("line.separator"));
				buffer.append("\t" + provider.toString());
			}
			buffer.append(System.getProperty("line.separator"));
			return buffer.toString();
		}

		@SuppressWarnings("unchecked")
        @Override
		public void readExternal(ObjectInput in) throws IOException,
				ClassNotFoundException {
			_shortName = (String) in.readObject();
			_displayName = (String) in.readObject();
			_providers = (Map<String, Provider>) in.readObject();
		}

		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeObject(_shortName);
			out.writeObject(_displayName);
			out.writeObject(_providers);
		}

	}

	public static class Provider implements Externalizable {

		private static final long serialVersionUID = -8586526277886308327L;

		private String _shortName;

		private String _displayName;

		public String getShortName() {
			return _shortName;
		}

		public void setShortName(String shortName) {
			_shortName = shortName;
		}

		public String getDisplayName() {
			return _displayName;
		}

		public void setDisplayName(String displayName) {
			_displayName = displayName;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((_displayName == null) ? 0 : _displayName.hashCode());
			result = prime * result
					+ ((_shortName == null) ? 0 : _shortName.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Provider other = (Provider) obj;
			if (_displayName == null) {
				if (other._displayName != null)
					return false;
			} else if (!_displayName.equals(other._displayName))
				return false;
			if (_shortName == null) {
				if (other._shortName != null)
					return false;
			} else if (!_shortName.equals(other._shortName))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Provider: [" + _shortName + "/" + _displayName + "]";
		}

		@Override
		public void readExternal(ObjectInput in) throws IOException,
				ClassNotFoundException {
			_shortName = (String) in.readObject();
			_displayName = (String) in.readObject();
		}

		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeObject(_shortName);
			out.writeObject(_displayName);
		}

	}

	@Override
	public void injectMetaDatas(Metadata metadata) {
		// TODO: adapt to new structure
		/*try {
			IPlugOperatorFinder operatorFinder = createOperatorFinder();
			Set<String> partnerSet = operatorFinder.findPartner();
			Set<String> providerSet = operatorFinder.findProvider();

			IPlugOperator oldPlugOperator = (IPlugOperator) metadata
					.getMetadata(IPLUG_OPERATOR);
			IPlugOperator newPlugOperator = createIPlugOperator(partnerSet,
					providerSet);
			if (!newPlugOperator.equals(oldPlugOperator)) {
				LOG.info("plug operator has been changed: " + newPlugOperator);
				metadata.addMetadata(IPLUG_OPERATOR, newPlugOperator);
			}

		} catch (IOException e) {
			LOG.warn(e.getMessage(), e);
		}*/

	}

	public abstract IPlugOperatorFinder createOperatorFinder();

	@Override
	public void setIBus(IBus bus) {
		_bus = bus;
	}

	private IPlugOperator createIPlugOperator(Set<String> partnerSet,
			Set<String> providerSet) {
		IPlugOperator operator = new IPlugOperator();

		List<?> allPartnerWithProvider = getAllPartnerWithProvider();

		for (Object object : allPartnerWithProvider) {
			@SuppressWarnings("unchecked")
            Map<Object, Object> partnerMap = (Map<Object, Object>) object;
			String partnerId = (String) partnerMap.get("partnerid");
			String partnerName = (String) partnerMap.get("name");

			Partner partner = new Partner();
			if (partnerSet.contains(partnerId)) {
				// partner is in index
				partner.setShortName(partnerId);
				partner.setDisplayName(partnerName);
				partnerSet.remove(partnerId);
			} else {
				// partner is not in index
				continue;
			}

			@SuppressWarnings("unchecked")
            List<Object> providerList = (List<Object>) partnerMap.get("providers");
			for (Object object2 : providerList) {
				@SuppressWarnings("unchecked")
                Map<Object, Object> providerMap = (Map<Object, Object>) object2;
				String providerId = (String) providerMap.get("providerid");
				String providerName = (String) providerMap.get("name");

				Provider provider = new Provider();
				if (providerSet.contains(providerId)) {
					provider.setShortName(providerId);
					provider.setDisplayName(providerName);
					providerSet.remove(providerId);
				} else {
					// provider is not in index
					continue;
				}
				partner.addProvider(provider);
			}
			operator.addPartner(partner);
		}

		for (String partnerId : partnerSet) {
			Partner partner = new Partner();
			partner.setShortName(partnerId);
			operator.addPartner(partner);
		}

		Partner unknownPartner = new Partner();
		unknownPartner.setShortName("unknown");
		for (String providerId : providerSet) {
			Provider provider = new Provider();
			provider.setShortName(providerId);
			unknownPartner.addProvider(provider);
		}
		if (!unknownPartner.getProviders().isEmpty()) {
			operator.addPartner(unknownPartner);
		}

		return operator;
	}

	private List<Object> getAllPartnerWithProvider() {
		List<Object> list = new ArrayList<Object>();
		try {
			if (_bus != null) {
				String query = "datatype:management management_request_type:1";
				IngridQuery ingridQuery = QueryStringParser.parse(query);
				IngridHits hits = _bus.search(ingridQuery, 1, 1, 1, 1000);
				if (hits != null && hits.length() > 0) {
					IngridHit hit = hits.getHits()[0];
					list = hit.getArrayList("partner");
				}
			}
		} catch (Exception e) {
			LOG.error("Error getting partner with providers", e);
		}
		return list;
	}

}
