import java.util.List;
// Records: basic, constructor, nesting, usage

// A record in a global context
record Ingredient(String name, String quantity, Unit unit) {
    public enum Unit {
        CUP, TABLESPOON, TEASPOON, GRAM, OUNCE, SLICE, PINCH, DASH
    }
}

public class F_Records_1 {
    // A record in a local context
    record Recipe(String name, List<Ingredient> ingredients, String instructions) { }

    public static void main(String[] args) {
        testRecord();
    }

    private static void testRecord() {
        Recipe chocolateCake = new Recipe(
                "Ultimate Chocolate Cake",
                List.of(
                        new Ingredient("flour", "2", Ingredient.Unit.CUP),
                        new Ingredient("sugar", "1.5", Ingredient.Unit.CUP),
                        new Ingredient("cocoa powder", "3/4", Ingredient.Unit.CUP),
                        new Ingredient("butter", "100", Ingredient.Unit.GRAM),
                        new Ingredient("Strawberry", "10", Ingredient.Unit.SLICE)
                ),
                "Preheat oven to 350°F. In a large bowl, combine dry ingredients..."
        );

        Recipe fruitSalad = new Recipe( "Fruit Salad" ,
                List.of( new Ingredient("Apple", "5", Ingredient.Unit.SLICE),
                        new Ingredient("Blueberry", "50", Ingredient.Unit.GRAM),
                        new Ingredient("Banana", "2", Ingredient.Unit.OUNCE),
                        new Ingredient("Grapes", "1", Ingredient.Unit.CUP),
                        new Ingredient("WhipCream", "2", Ingredient.Unit.DASH)),
                "Cut the fruits in supervision of grownups only. Cut fruits in equal sizes");


        System.out.println("Name of Chocolate cake: " + chocolateCake.name());
        System.out.println("Ingredients for Fruit Salad " + fruitSalad.ingredients);
        System.out.println("Amount of Flour Needed for Chocolate cake: " + chocolateCake.ingredients.getFirst());
    }
}

