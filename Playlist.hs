module Playlist (Playlist, nuevaP, actualP, skipP, backP, resetP) where

import Tema (Tema)
import Tipos ()

data Playlist = Play Int [Tema] deriving (Eq, Show)

-- expongo el tipo y las operaciones-- --tratamos de evitar exponer la estructura del tipo--

nuevaP :: [Tema] -> Playlist
-- A partir de una lista de temas crea una nueva Playlist con su indice en cero--
nuevaP temas = Play 0 temas

-- reduzco la funcion--

actualP :: Playlist -> Tema
-- Dada una Playlist devuelve el tema en la posicion indicada por el indice--
actualP (Play i temas) = temas !! i

-- i = indice--

skipP :: Playlist -> Playlist
-- Devuelve una Playlist con su indice aumentado en uno--
skipP (Play i temas) = Play (min (i + 1) (length temas - 1)) temas

backP :: Playlist -> Playlist
-- idem anterior pero con el indice decrementado en uno--
backP (Play i temas) = Play (max (i - 1) 0) temas

resetP :: Playlist -> Playlist
-- Dada una Playlist crea una nueva con la lista de temas de la original--
resetP (Play _ temas) = Play 0 temas

{-
nuevaP :: [Tema] -> Playlist
nuevaP temas = Play 0 temas

actualP :: Playlist -> Tema
actualP (Play i temas) = temas !! i

skipP :: Playlist -> Playlist
skipP (Play i temas) = Play (min (i+1) (length temas - 1)) temas

backP :: Playlist -> Playlist
backP (Play i temas) = Play (max (i-1) 0) temas

resetP :: Playlist -> Playlist
resetP (Play _ temas) = Play 0 temas
-}