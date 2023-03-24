module FileSystem (FileSystem, nuevoF, etiquetasF, temasF, agregarF, filtrarF) where

import Data.List (foldl')
import Tema
import Tipos

data FileSystem = FS [Etiqueta] [Tema] deriving (Eq, Show)

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

-- Agrega el tema y sus etiquetas de ser necesario.--
filtrarF :: Etiqueta -> FileSystem -> [Tema]
filtrarF etiqueta (FS _ temas) = filter (aplicaT etiqueta) temas

flowers = agregarT "Disco" (agregarT "Pop" (agregarT "Funk" (nuevoT "Flowers" "Miley Cyrus")))

fixYou = agregarT "Rock" (nuevoT "Fix You" "Coldplay")

plasticHearts = agregarT "Pop" (agregarT "Rock" (nuevoT "Plastic Hearts" "Miley Cyrus"))

greatestLove = agregarT "Pop" (nuevoT "Greatest Love of All" "Whitney Houston")

testing =
  [ nuevoF == FS [] [],
    etiquetasF (FS ["Pop", "Funk", "Disco", "Rock"] [flowers, fixYou, plasticHearts]) == ["Pop", "Funk", "Disco", "Rock"],
    temasF (FS ["Pop", "Funk", "Disco", "Rock"] [flowers, fixYou, plasticHearts]) == [flowers, fixYou, plasticHearts],
    agregarF (greatestLove) (FS ["Pop", "Funk", "Disco", "Rock"] [flowers, fixYou, plasticHearts]) == FS ["Pop", "Funk", "Disco", "Rock"] [flowers, fixYou, plasticHearts, greatestLove],
    filtrarF "Rock" (FS ["Pop", "Funk", "Disco", "Rock"] [flowers, fixYou, plasticHearts]) == [fixYou, plasticHearts],
    filtrarF "Pop" (FS ["Pop", "Funk", "Disco", "Rock"] [flowers, fixYou, plasticHearts]) == [flowers, plasticHearts],
    filtrarF "Funk" (FS ["Pop", "Funk", "Disco", "Rock"] [flowers, fixYou, plasticHearts]) == [flowers],
    filtrarF "Electronic" (FS ["Pop", "Funk", "Disco", "Rock"] [flowers, fixYou, plasticHearts]) == []
  ]