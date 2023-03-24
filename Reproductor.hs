module Reproductor (Reproductor, nuevoR, archivosR, listaParaR, temasR, playR, actualR, avanzarR, retrocederR, reiniciarR) where

import FileSystem
import Playlist
import Tema
import Tipos

data Reproductor = RP FileSystem Playlist deriving (Eq, Show)

nuevoR :: FileSystem -> Reproductor
-- Crea un nuevo reproductor a partir de un FileSystem y una nueva Playlist con su lista de temas vacıa--
nuevoR fs = RP fs (nuevaP [])

archivosR :: Reproductor -> FileSystem
archivosR (RP fs _) = fs

listaParaR :: Etiqueta -> Reproductor -> [Tema]
listaParaR etiqueta (RP fs _) = filtrarF etiqueta fs

temasR :: Reproductor -> [Tema]
temasR rp = temasF (archivosR rp)

-- COMO HACER PARA USAR EL CONSTRUCTOR DE OTRO--
-- temasR (RP _ pl) = actualP pl--

playR :: Reproductor -> Etiqueta -> Reproductor
playR (RP fs playlist) etiqueta = RP fs (nuevaP (filtrarF etiqueta fs))

actualR :: Reproductor -> Tema
actualR (RP _ playlist) = actualP playlist

-- chequear si usar pl o p como en el modulo playlist--

avanzarR :: Reproductor -> Reproductor
avanzarR (RP fs playlist) = RP fs (skipP playlist)

retrocederR :: Reproductor -> Reproductor
retrocederR (RP fs playlist) = RP fs (backP playlist)

reiniciarR :: Reproductor -> Reproductor
reiniciarR (RP fs playlist) = RP fs (resetP playlist)

flowers = agregarT "Disco" (agregarT "Pop" (agregarT "Funk" (nuevoT "Flowers" "Miley Cyrus")))

fixYou = agregarT "Rock" (nuevoT "Fix You" "Coldplay")

plasticHearts = agregarT "Pop" (agregarT "Rock" (nuevoT "Plastic Hearts" "Miley Cyrus"))

fileSystem = agregarF plasticHearts (agregarF fixYou (agregarF flowers (nuevoF)))

playlist = nuevaP [flowers, fixYou, plasticHearts]

testing =
  [ nuevoR fileSystem == RP fileSystem (nuevaP []),
    archivosR (RP fileSystem playlist) == fileSystem,
    listaParaR "Pop" (RP fileSystem playlist) == [flowers, plasticHearts],
    temasR (RP fileSystem playlist) == [flowers, fixYou, plasticHearts],
    actualR (RP fileSystem playlist) == flowers,
    actualR (playR (RP fileSystem playlist) "Rock") == fixYou,
    actualR (avanzarR (RP fileSystem playlist)) == fixYou,
    actualR (retrocederR (RP fileSystem (skipP playlist))) == flowers,
    actualR (reiniciarR (RP fileSystem (skipP playlist))) == flowers
  ]