package org.gonevertical.project.client.activity;

import org.gonevertical.project.client.ClientFactory;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.PlaceHistoryMapperWithFactory;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of. This is done via the @WithTokenizers
 * annotation or by extending PlaceHistoryMapperWithFactory and creating a
 * separate TokenizerFactory.
 * 
 * NOTE: the clientFactory has tokenizers Get methods for The HistoryMapper!!!!!!!!!!!!!!!!!
 * 
 * A {@link PlaceHistoryMapper} that can get its {@link PlaceTokenizer}
 * instances from a factory.
 * 
 * { @link ClientFactory } - tokenizers are here!!!!!!
 * 
 * @param <F> factory type
 */
// @WithTokenizers({ LoginPlace.Tokenizer.class, ... })
public interface ApplicationPlaceHistoryMapper extends PlaceHistoryMapperWithFactory<ClientFactory> {
  /**
   * { @link ClientFactory }
   * ClientFactory has the tokenizer get methods!!!!
   */
}
