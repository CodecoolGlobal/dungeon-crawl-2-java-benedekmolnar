DROP TABLE IF EXISTS public.game_state CASCADE ;
CREATE TABLE public.game_state (
                                   id serial NOT NULL PRIMARY KEY,
                                   player_name text NOT NULL,
                                   map text NOT NULL,
                                   saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS public.actors;
CREATE TABLE public.actors (
                               id serial NOT NULL PRIMARY KEY,
                               game_state_id integer NOT NULL,
                               hp integer NOT NULL,
                               x integer NOT NULL,
                               y integer NOT NULL,
                               direction text NULL,
                               data text
);

DROP TABLE IF EXISTS public.items;
CREATE TABLE public.items (
                              id serial NOT NULL PRIMARY KEY,
                              game_state_id integer NOT NULL,
                              type text NOT NULL,
                              x integer NOT NULL,
                              y integer NOT NULL
);

DROP TABLE IF EXISTS public.inventory;
CREATE TABLE public.inventory (
                                  id serial NOT NULL PRIMARY KEY,
                                  game_state_id integer NOT NULL,
                                  type text NOT NULL,
                                  arrow integer NOT NULL,
                                  key integer NOT NULL
);

ALTER TABLE ONLY public.actors
    ADD CONSTRAINT fk_game_state_id FOREIGN KEY (game_state_id) REFERENCES public.game_state(id);

ALTER TABLE ONLY public.items
    ADD CONSTRAINT fk_game_state_id FOREIGN KEY (game_state_id) REFERENCES public.game_state(id);

ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT fk_game_state_id FOREIGN KEY (game_state_id) REFERENCES public.game_state(id);