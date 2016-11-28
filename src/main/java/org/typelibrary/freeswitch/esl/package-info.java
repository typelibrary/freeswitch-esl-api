/**
 * This API library specifies the minimal types and interfaces for the interaction between
 * application code and the Freeswitch Event Socket Library (ESL) protocol. This library
 * <em>does not</em> implement the protocol, but merely specifies how application code and the
 * protocol should interact.
 * 
 * <p>
 * The API library itself is not designed to enforce the ESL protocol, rather it is up to
 * library implementations to determine to what extent their implementation should follow
 * the protocol. The API library does make some assumptions about the protocol behavior,
 * which may or may not constrain library implementations, and these are identified on
 * a case by case basis in the API documentation and code.
 * </p>
 * 
 * <p>
 * It should be noted that the ESL protocol is not separately defined as a stand-alone
 * specification. The rules of the protocol are derived by the Freeswitch server implementation
 * of the protocol, and to a lesser extent, the Freeswitch ESL client implementation. It is up to 
 * library implementors to correctly measure, or interpret, the Freeswitch server implementation 
 * and design their library accordingly.
 * </p>
 */
package org.typelibrary.freeswitch.esl;