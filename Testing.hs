module Testing (Playlist, nuevaP) where

import Tema (Tema)
import Tipos ()

data Playlist = Play Int [Tema] deriving (Eq, Show)

nuevaP :: [Tema] -> Playlist
-- A partir de una lista de temas crea una nueva Playlist con su indice en cero--
nuevaP temas = Play 0 temas
