package fr.faygwenn.practice.utils;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class Replaceable<T> {
    protected Map<String, String> replacements;

    public Replaceable() {
        this.replacements = new HashMap<String, String>();
    }

    @SuppressWarnings("unchecked")
    public T replace(String toReplace, Object replacement) {
        if (replacements.containsKey(toReplace))
            replacements.remove(toReplace);

        replacements.put(toReplace, String.valueOf(replacement));

        return (T) this;
    }

    public String replaceFormat(String replaceFormat) {
        String result = replaceFormat;

        try {
            for (String toReplace : replacements.keySet())
                result = result.replace(toReplace, replacements.get(toReplace));

            return Message.format(result);
        } catch (ConcurrentModificationException exception) {
            return Message.format(result);
        }
    }

    public LinkedList<String> cloneReplaceFormat(LinkedList<String> replaceFormat) {
        LinkedList<String> result = new LinkedList<String>();

        for (String line : replaceFormat)
            result.add(replaceFormat(line));

        return result;
    }
}