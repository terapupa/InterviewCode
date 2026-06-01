package patterns.abstractFactory

interface GuiFactory {
    fun createButton(): Button

    fun createCheckbox(): Checkbox
}

interface Button {
    fun render()

    fun onClick()
}

interface Checkbox {
    fun render()

    fun onCheck()
}

class WinFactory : GuiFactory {
    override fun createButton(): Button =
        object : Button {
            override fun render() = println("render WinButton")

            override fun onClick() = println("onClick WinButton")
        }

    override fun createCheckbox(): Checkbox =
        object : Checkbox {
            override fun render() = println("render WinCheckbox")

            override fun onCheck() = println("onCheck WinCheckbox")
        }
}

class MacFactory : GuiFactory {
    override fun createButton(): Button =
        object : Button {
            override fun render() = println("render MacButton")

            override fun onClick() = println("onClick MacButton")
        }

    override fun createCheckbox(): Checkbox =
        object : Checkbox {
            override fun render() = println("render MacCheckbox")

            override fun onCheck() = println("onCheck MacCheckbox")
        }
}

fun main() {
    val factory: GuiFactory =
        if (System.getProperty("os.name").lowercase().contains("win")) {
            WinFactory()
        } else {
            MacFactory()
        }
    val button = factory.createButton()
    val checkbox = factory.createCheckbox()
    button.render()
    button.onClick()
    checkbox.render()
    checkbox.onCheck()
}
