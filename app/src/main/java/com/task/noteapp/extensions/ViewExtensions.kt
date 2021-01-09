package com.task.noteapp.extensions

import android.view.View


/*
* Show the view  (visibility = View.VISIBLE)
*/
fun View.show() : View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

/*
 * Invisible the view (visibility = View.INVISIBLE
 */
fun View.invisible(): View {
    if(visibility != View.INVISIBLE){
        visibility = View.INVISIBLE
    }
    return this
}

/**
 *
 * Show the view if [condition] returns true
 * (visibility = View.VISIBLE)
 */
inline fun View.showIf(condition: () -> Boolean) : View {
    if (visibility != View.VISIBLE ) {
        visibility = View.VISIBLE
    }
    return this
}

/**
 * Remove the view (visibility = View.GONE)
 */
fun View.hide() : View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

/**
 * Remove the view if [predicate] returns true
 * (visibility = View.GONE)
 */
inline fun View.hideIf(predicate: () -> Boolean) : View {
    if (visibility != View.GONE ) {
        visibility = View.GONE
    }
    return this
}