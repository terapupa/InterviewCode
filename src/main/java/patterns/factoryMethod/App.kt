package patterns.factoryMethod

abstract class Dialog {
    abstract fun createButton(): Button
}

interface Button {
    fun render()
    fun onClick()
}

class WinDialog: Dialog() {
    override fun createButton(): Button = object : Button {
        override fun render() = println("render WinButton")
        override fun onClick() = println("onClick WinButton")
    }
}

class MacDialog: Dialog() {
    override fun createButton(): Button = object : Button {
        override fun render() = println("render MacButton")
        override fun onClick() = println("onClick MacButton")
    }
}

fun main() {
    val dialog: Dialog = if (System.getProperty("os.name").lowercase().contains("win")) {
        WinDialog()
    } else {
        MacDialog()
    }
    val button = dialog.createButton()
    button.render()
    button.onClick()
}

