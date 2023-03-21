module Tipos where

type Datos = String

type Etiqueta = String

type Nombre = String

-- La función toma un elemento x y una lista ys, y devuelve una nueva lista con el elemento añadido en la posición correcta para mantener el orden de la lista original.--
-- La restricción Ord a indica que los elementos de la lista deben ser ordenables, lo que es necesario para compararlos mediante el operador <=.--

insertar :: Ord a => a -> [a] -> [a]
insertar x [] = [x] -- si la lista esta vacia, agregar el elemento y devolver una lista simple--
insertar x (y : ys)
  | x <= y = x : y : ys -- si x es menor o igual al primer elemento, insertarlo al principio--
  -- la expresión x : y : ys construye una lista que comienza con el elemento x, seguido por el elemento y, y luego los elementos restantes de la lista ys.--
  | otherwise = y : insertar x ys -- otherwise, insertarlo recursivamente al final de la lista--
