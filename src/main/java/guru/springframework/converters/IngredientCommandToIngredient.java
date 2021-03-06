package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
	private UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
		this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
	}

	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand source) {
		if (source == null)
			return null;
		final Ingredient ingredient = new Ingredient();
		ingredient.setId(source.getId());
		ingredient.setDescription(source.getDescription());
		ingredient.setAmount(source.getAmount());
		ingredient.setRecipe(source.getRecipe());
		ingredient.setUnitOfMeasure(unitOfMeasureCommandToUnitOfMeasure.convert(source.getUnitOfMeasure()));
		return ingredient;
	}

}
