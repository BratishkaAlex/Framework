package elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class BaseForm {
    protected HashMap<String, BaseElement> elements;

    public BaseForm() {
        elements = new HashMap<>();
    }

    public void addElement(String name, BaseElement element){
        elements.put(name, element);
    }

    public BaseElement navigateTo(String name){
        return elements.get(name);
    }

    public List<BaseElement> getElements(){
        return  new ArrayList<>(elements.values());
    }

    public void click(String elementName){
        navigateTo(elementName).click();
    }


}
