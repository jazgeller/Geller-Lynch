module FileSystem (FileSystem, nuevoF, etiquetasF, temasF, agregarF, filtrarF) where

import Tema
import Tipos

data FileSystem = FS [Etiqueta] [Tema] deriving (Eq, Show)

-- no se puede usar pattern matching sobre temas y etiquetas --
nuevoF :: FileSystem
nuevoF = FS [] []

-- Crea un nuevo FileSystem con sus listas vacias--
-- El tipo de datos FileSystem tiene dos campos: una lista de valores de etiqueta y una lista de valores de tema.
-- En este caso, creamos un nuevo sistema de archivos con listas vacías para ambos campos usando el constructor FS.--
etiquetasF :: FileSystem -> [Etiqueta]
etiquetasF (FS etiquetas _) = etiquetas

temasF :: FileSystem -> [Tema]
temasF (FS _ temas) = temas

agregarF :: Tema -> FileSystem -> FileSystem
agregarF t (FS etiquetas temas) = FS nuevasEtiquetas nuevosTemas
  where
    nuevasEtiquetas = etiquetas ++ filter (`notElem` etiquetas) (etiquetasT t)
    nuevosTemas = temas ++ [t]

-- CHEQUEAR-- -!!!!--

-- Primero utilizamos la coincidencia de patrones para extraer las listas de etiquetas y temas del sistema de archivos. Definimos entonces dos nuevas listas: nuevasEtiquetas y nuevosTemas.--

-- Agrega el tema y sus etiquetas de ser necesario.--
filtrarF :: Etiqueta -> FileSystem -> [Tema]
filtrarF etiqueta (FS _ temas) = filter (aplicaT etiqueta) temas