Spring Cloud Messaging with Function Syntax
----

The intent of this project structure is to provide a medium-sized example project that leverages spring-cloud-messaging 
using spring function's syntax.

There is a rabbitmq management panel available on port 15672.

## Project Problem Space

The domain is a simplified pharmacy claim processing engine. The end result should be a web app that 
presents information on members and their associated pharmaceutical claims. 

The problem roadmap for this project is:

1. Finding a clear way to run 'system' integration tests that actually run through messaging middleware
2. Providing examples of common message handling needs, like error handling, "Maybe" emitting an event after processing 
   (i.e `Function<IncomingEvent, Optional<OutgoingEvent>`), and message acknowledgment.
  
Project sub-domains are mapped to gradle sub-projects (member, transaction-ingestion, etc). This provides compile-time 
guards against unintentionally crossing a context boundary. Since many of these domains communicate using messages with
similar structures, it can be tempting to quickly leverage a "shared-kernel" for DTOs. This is a bit of a trap as very
quickly these objects become weighed down by validation and domain logic that are needed by several domains.

## Useful links

Spring Cloud Stream Samples - https://github.com/spring-cloud/spring-cloud-stream-samples
Integrating foreign sources into event system - https://docs.spring.io/spring-cloud-stream/docs/current/reference/html/spring-cloud-stream.html#_sending_arbitrary_data_to_an_output_e_g_foreign_event_driven_sources

