package com.group.nameinverter

class NameInverter {

    lateinit var name: String

    private fun formatName(names: MutableList<String>): String {
        if (names.size == 1) {
            return names[0]
        }
        return formatMultiElementName(names)
    }

    private fun formatMultiElementName(names: MutableList<String>): String {
        var postNominal = ""
        if (names.size > 2) postNominal = getPostNominal(names)
        return "${names[1]}, ${names[0]} $postNominal".trim()
    }

    private fun getPostNominal(names: MutableList<String>): String {
        var  postNominals = names.subList(2, names.size)
        return postNominals.joinToString(" ")
    }

    private fun removeHonorific(names: MutableList<String>): MutableList<String> {
        if (names.size > 1 && isHonorific(names)) names.removeAt(0)
        return names
    }

    private fun splitNames(name: String) = name.trim().split("\\s+".toRegex()) as MutableList<String>
    private fun isHonorific(names: MutableList<String>) =
//        (names[0] == "Mr." || names[0] == "Mrs.")
        names[0].matches("Mr\\.|Mrs\\.".toRegex())

    fun invert(name: String?): String {
        if (name.isNullOrEmpty()) return ""
        else return formatName(removeHonorific(splitNames(name)))
    }
}