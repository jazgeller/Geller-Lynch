module Playlist (Playlist, nuevaP, actualP, skipP, backP, resetP) where

import Tema
import Tipos

data Playlist = Play Int [Tema] deriving (Eq, Show)

nuevaP :: [Tema] -> Playlist
-- A partir de una lista de temas crea una nueva Playlist con su indice en cero--
nuevaP temas = Play 0 temas

actualP :: Playlist -> Tema
-- Dada una Playlist devuelve el tema en la posicion indicada por el indice--
actualP (Play i temas) = temas !! i

-- i = indice--

skipP :: Playlist -> Playlist
-- Devuelve una Playlist con su indice aumentado en uno--
skipP (Play i temas) = Play (min (i + 1) (length temas - 1)) temas

backP :: Playlist -> Playlist
-- igual al anterior pero con el indice decrementado en uno--
backP (Play i temas) = Play (max (i - 1) 0) temas

resetP :: Playlist -> Playlist
-- Dada una Playlist crea una nueva con la lista de temas de la original--
resetP (Play _ temas) = Play 0 temas

flowers = agregarT "Disco" (agregarT "Pop" (agregarT "Funk" (nuevoT "Flowers" "Miley Cyrus")))

fixYou = agregarT "Rock" (nuevoT "Fix You" "Coldplay")

plasticHearts = agregarT "Pop" (agregarT "Rock" (nuevoT "Plastic Hearts" "Miley Cyrus"))

testing =
  [ nuevaP [flowers, fixYou, plasticHearts] == Play 0 [flowers, fixYou, plasticHearts],
    actualP (Play 0 [flowers, fixYou, plasticHearts]) == flowers,
    skipP (Play 0 [flowers, fixYou, plasticHearts]) == Play 1 [flowers, fixYou, plasticHearts],
    skipP (Play 1 [flowers, fixYou, plasticHearts]) == Play 2 [flowers, fixYou, plasticHearts],
    backP (Play 2 [flowers, fixYou, plasticHearts]) == Play 1 [flowers, fixYou, plasticHearts],
    resetP (Play 0 [flowers, fixYou, plasticHearts]) == Play 0 [flowers, fixYou, plasticHearts]
  ]
