package com.vsouhrada.apps.fibo.injection

/**
 * * This interface should be implemented by all classes which should create a component. Mainly all activities which contains some
 * injectable field.

 * @param[C] component class
 * *
 * @author vsouhrada
 * *
 * @version 0.1
 * *
 * @since 0.1
 */
interface HasComponent<out C> {

    /**
     * This method is call in case of that it is a right time to create a new instance of component.

     * @since 1.0.0
     */
    fun onCreateComponent()

    /**
     * This method is call in case of that class which implements this interface is coming to be destroy. Inside of this method you should
     * typically add `component = null;`

     * @since 1.0.0
     */
    fun onDestroyComponent()

    /**
     * Returns component instance

     * @return instance of component
     * *
     * @since 1.0.0
     */
    val component: C

}
