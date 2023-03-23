module Tema (Tema, nuevoT, nombreT, datosT, etiquetasT, agregarT, aplicaT) where

import Tipos

data Tema = Tem Nombre [Etiqueta] Datos deriving (Eq, Show, Ord)

nuevoT :: Nombre -> Datos -> Tema
nuevoT nombre datos = Tem nombre [] datos

-- nuevoT: Esta función toma un Nombre y Datos como argumentos y devuelve un nuevo Tema con el nombre y datos dados, y una lista vacía de etiquetas.--
nombreT :: Tema -> Nombre
nombreT (Tem nombre _ _) = nombre

{-nombreT: Esta función toma un Tema como argumento y devuelve su nombre.
Como no estamos usando los otros dos campos en el Tema,
usamos _ para indicar que no estamos interesados en vincularlos a una variable.-}

datosT :: Tema -> Datos
datosT (Tem _ _ datos) = datos

-- datosT: Esta función toma un Tema como argumento y devuelve sus datos.--

etiquetasT :: Tema -> [Etiqueta]
etiquetasT (Tem _ etiquetas _) = etiquetas

-- etiquetasT: Esta función toma un Tema como argumento y devuelve su lista de etiquetas.--

agregarT :: Etiqueta -> Tema -> Tema
agregarT etiqueta (Tem nombre etiquetas datos) = Tem nombre (etiqueta : etiquetas) datos

-- agregarT: Esta función toma una Etiqueta y un Tema como argumentos y devuelve un nuevo Tema con la etiqueta dada añadida a su lista de etiquetas.--

aplicaT :: Etiqueta -> Tema -> Bool
aplicaT etiqueta (Tem _ etiquetas _) = etiqueta `elem` etiquetas

-- aplicaT: Esta función toma una Etiqueta y un Tema como argumentos y devuelve True si la etiqueta dada está en la lista de etiquetas del tema, y False en caso contrario.--
{-Este patrón de implementación coincide con el Tema para extraer su lista de etiquetas y,
a continuación, utiliza la función elem para comprobar si la etiqueta dada está en la lista de etiquetas.
La función devuelve True si la etiqueta está en la lista de etiquetas, y False en caso contrario.-}

testing =
  [ nuevoT "Flowers" "Miley Cyrus" == Tem "Flowers" [] "Miley Cyrus",
    nombreT (Tem "Flowers" ["Pop", "Funk"] "Miley Cyrus") == "Flowers",
    datosT (Tem "Flowers" ["Pop", "Funk"] "Miley Cyrus") == "Miley Cyrus",
    etiquetasT (Tem "Flowers" ["Pop", "Funk"] "Miley Cyrus") == ["Pop", "Funk"],
    agregarT "Disco" (Tem "Flowers" ["Pop", "Funk"] "Miley Cyrus") == (Tem "Flowers" ["Disco", "Pop", "Funk"] "Miley Cyrus"),
    aplicaT "Pop" (Tem "Flowers" ["Pop", "Funk"] "Miley Cyrus") == True
  ]