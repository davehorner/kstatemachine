package ru.nsk.kstatemachine

/**
 * Base interface for events which may trigger transitions of [StateMachine]
 */
interface Event

/**
 * Event holding some data
 */
interface DataEvent<out D : Any> : Event {
    val data: D
}

/**
 * User may call [StateMachine.processEvent] with [UndoEvent] as alternative to calling machine.undo()
 */
object UndoEvent : Event

/**
 * System event which is used by the library to wrap original event and argument,
 * so user may access them, when this event is processed.
 * Currently only [UndoEvent] is transformed to this event.
 * @param event original event
 * @param argument original argument
 */
class WrappedEvent(val event: Event, val argument: Any?) : Event

@StateMachineDslMarker
data class TransitionParams<E : Event>(
    val transition: Transition<E>,
    val direction: TransitionDirection,
    val event: E,
    /**
     * This parameter may be used to pass arbitrary data with the event,
     * so there is no need to define [Event] subclasses every time.
     * Subclassing should be preferred if the event always contains data of some type.
     */
    val argument: Any? = null,
)

/**
 * Convenience property for unwrapping original event.
 * If the event is not [WrappedEvent] this is same as [TransitionParams.event] property
 */
val TransitionParams<*>.unwrappedEvent get() = if (event is WrappedEvent) event.event else event

/**
 * Convenience property for unwrapping original argument.
 * If the event is not [WrappedEvent] this is same as [TransitionParams.argument] property
 */
val TransitionParams<*>.unwrappedArgument get() = if (event is WrappedEvent) event.argument else argument