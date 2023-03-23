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
listaParaR e (RP fs _) = filtrarF e fs

-- e = Etiqueta--

temasR :: Reproductor -> [Tema]
temasR rp = temasF (archivosR rp)

-- COMO HACER PARA USAR EL CONSTRUCTOR DE OTRO--
-- temasR (RP _ pl) = actualP pl--

playR :: Reproductor -> Etiqueta -> Reproductor
playR (RP fs playlist) label = RP fs (nuevaP (filtrarF label fs))

actualR :: Reproductor -> Tema
actualR (RP _ playlist) = actualP playlist

-- chequear si usar pl o p como en el modulo playlist--

avanzarR :: Reproductor -> Reproductor
avanzarR (RP fs playlist) = RP fs (skipP playlist)

retrocederR :: Reproductor -> Reproductor
retrocederR (RP fs playlist) = RP fs (backP playlist)

reiniciarR :: Reproductor -> Reproductor
reiniciarR (RP fs _) = RP fs (resetP playlist)

{-
-- Tener el tema actual de la playlist de la persona--
actualR :: Reproductor -> Tema --CHEQUEAR--
actualR (RP _ playlist) = P.actualP playlist

avanzar R, RetrocederR y reiniciarR

-- Crear un nuevo reproductor con una lista de reproducción que contenga el tema dado
-- y sus etiquetas añadidas al sistema de archivos si es necesario
agregarTema :: Tema -> Reproductor -> Reproductor
agregarTema tema (RP (FS etiquetas temas) playlist) = RP (FS etiquetas temas') playlist'
    where temas' = agregarTemaFS tema temas etiquetas
          playlist' = agregarF tema (RP (FS etiquetas temas') playlist)-}