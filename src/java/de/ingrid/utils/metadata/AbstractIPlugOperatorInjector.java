package de.ingrid.utils.metadata;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import de.ingrid.utils.IBus;
import de.ingrid.utils.IngridHit;
import de.ingrid.utils.IngridHits;
import de.ingrid.utils.query.IngridQuery;
import de.ingrid.utils.queryparser.QueryStringParser;

public abstract class AbstractIPlugOperatorInjector implements
		IMetadataInjector, IBusable {

	public static final String IPLUG_OPERATOR = "IPLUG_OPERATOR";

	private Logger LOG = Logger
			.getLogger(AbstractIPlugOperatorInjector.class.getName());

	private IBus _bus;

	public class IPlugOperator implements Serializable {

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

	}

	public class Partner implements Serializable {

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

	}

	public class Provider implements Serializable {

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
	}

	@Override
	public void injectMetaDatas(Metadata metadata) {
		try {
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
			LOG.warning(e.getMessage());
		}

	}

	public abstract IPlugOperatorFinder createOperatorFinder();

	@Override
	public void setIBus(IBus bus) {
		_bus = bus;
	}

	private IPlugOperator createIPlugOperator(Set<String> partnerSet,
			Set<String> providerSet) {
		IPlugOperator operator = new IPlugOperator();

		List allPartnerWithProvider = getAllPartnerWithProvider();

		for (Object object : allPartnerWithProvider) {
			Map partnerMap = (Map) object;
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

			List providerList = (List) partnerMap.get("providers");
			for (Object object2 : providerList) {
				Map providerMap = (Map) object2;
				String providerId = (String) providerMap.get("providerid");
				String providerName = (String) providerMap.get("name");

				Provider provider = new Provider();
				if (providerSet.contains(providerId)) {
					provider.setShortName(providerId);
					provider.setDisplayName(providerName);
					providerMap.remove(providerId);
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
		operator.addPartner(unknownPartner);

		return operator;
	}

	private List getAllPartnerWithProvider() {
		List list = new ArrayList();
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
			e.printStackTrace();
		}
		return list;
	}

}
