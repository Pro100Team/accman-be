--liquibase formatted sql
--changeset "ivkam":3
INSERT INTO public.default_subcategory(
    def_sub_type, def_sub_color, def_sub_name, def_sub_parent_cat_id)
VALUES
    (0, 'FF0000', 'Mortgage', 5),
    (0, 'FF0000', 'Rent', 5),
    (0, 'FF0000', 'Utilities', 5),
    (0, 'FF0000', 'Chemicals and household items', 5),
    (0, 'FF0000', 'Car insurance', 6),
    (0, 'FF0000', 'Health insurance', 6),
    (0, 'FF0000', 'Groceries', 7),
    (0, 'FF0000', 'Eating out', 7),
    (0, 'FF0000', 'Hobbies', 8),
    (0, 'FF0000', 'Books', 8),
    (0, 'FF0000', 'Cinema, theater, museum, concert', 8),
    (0, 'FF0000', 'Subscriptions ', 8),
    (0, 'FF0000', 'Celebrations & gifts', 8),
    (0, 'FF0000', 'Medicines & pharmacy', 9),
    (0, 'FF0000', 'Sports & gym', 9),
    (0, 'FF0000', 'Public transport & train', 12),
    (0, 'FF0000', 'Petrol', 12),
    (0, 'FF0000', 'Road tolls & parking', 12),
    (0, 'FF0000', 'Children products', 15),
    (0, 'FF0000', 'Toys', 15),
    (0, 'FF0000', 'Education', 15);