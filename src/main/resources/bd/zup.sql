PGDMP
         $    
        	    w            api    9.4.22    9.4.22 "               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                        0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            !           1262    16398    api    DATABASE     u   CREATE DATABASE api WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';
    DROP DATABASE api;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            "           0    0 
   SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    7            #           0    0 
   SCHEMA public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    7                        3079    11897    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            $           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1                        3079    16709    unaccent 	   EXTENSION     <   CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA public;
    DROP EXTENSION unaccent;
                  false    7            %           0    0    EXTENSION unaccent    COMMENT     P   COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';
                       false    2            �            1259    21918 	   addresses    TABLE     m  CREATE TABLE public.addresses (
    id bigint NOT NULL,
    created timestamp without time zone DEFAULT now(),
    deleted boolean DEFAULT false,
    updated timestamp without time zone,
    complement character varying(255),
    neighborhood character varying(255),
    number integer,
    street character varying(255),
    zip_code bigint,
    user_id bigint
);
    DROP TABLE public.addresses;
       public         api    false    7            �            1259    21916    addresses_id_seq    SEQUENCE     y   CREATE SEQUENCE public.addresses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.addresses_id_seq;
       public       api    false    175    7            &           0    0    addresses_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.addresses_id_seq OWNED BY public.addresses.id;
            public       api    false    174            �            1259    21929    phones    TABLE     _   CREATE TABLE public.phones (
    user_id bigint NOT NULL,
    number character varying(255)
);
    DROP TABLE public.phones;
       public         api    false    7            �            1259    21932    roles    TABLE     M   CREATE TABLE public.roles (
    user_id bigint NOT NULL,
    role integer
);
    DROP TABLE public.roles;
       public         api    false    7            �            1259    21937    users    TABLE     �  CREATE TABLE public.users (
    id bigint NOT NULL,
    created timestamp without time zone DEFAULT now(),
    deleted boolean DEFAULT false,
    updated timestamp without time zone,
    description character varying(255),
    name character varying(255) NOT NULL,
    birth_date timestamp without time zone NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255),
    social_code bigint NOT NULL,
    user_type integer NOT NULL
);
    DROP TABLE public.users;
       public         api    false    7            �            1259    21935    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public       api    false    7    179            '           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
            public       api    false    178            �           2604    21921    id    DEFAULT     l   ALTER TABLE ONLY public.addresses ALTER COLUMN id SET DEFAULT nextval('public.addresses_id_seq'::regclass);
 ;   ALTER TABLE public.addresses ALTER COLUMN id DROP DEFAULT;
       public       api    false    174    175    175            �           2604    21940    id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public       api    false    179    178    179                      0    21918 	   addresses 
   TABLE DATA                  COPY public.addresses (id, created, deleted, updated, complement, neighborhood, number, street, zip_code, user_id) FROM stdin;
    public       api    false    175   e#       (           0    0    addresses_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.addresses_id_seq', 1, false);
            public       api    false    174                      0    21929    phones 
   TABLE DATA               1   COPY public.phones (user_id, number) FROM stdin;
    public       api    false    176   �#                 0    21932    roles 
   TABLE DATA               .   COPY public.roles (user_id, role) FROM stdin;
    public       api    false    177   �#                 0    21937    users 
   TABLE DATA               �   COPY public.users (id, created, deleted, updated, description, name, birth_date, email, password, social_code, user_type) FROM stdin;
    public       api    false    179   �#       )           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 1, false);
            public       api    false    178            �           2606    21928    addresses_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.addresses DROP CONSTRAINT addresses_pkey;
       public         api    false    175    175            �           2606    21949    uk_6dotkott2kjsp8vw4d0m25fb7 
   CONSTRAINT     ^   ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);
 L   ALTER TABLE ONLY public.users DROP CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7;
       public         api    false    179    179            �           2606    21951    uk_h3d2xc5iper6aev9bkc9fpkb3 
   CONSTRAINT     d   ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_h3d2xc5iper6aev9bkc9fpkb3 UNIQUE (social_code);
 L   ALTER TABLE ONLY public.users DROP CONSTRAINT uk_h3d2xc5iper6aev9bkc9fpkb3;
       public         api    false    179    179            �           2606    21947 
   users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public         api    false    179    179            �           2606    21952    fk1fa36y2oqhao3wgg2rw1pi459 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT fk1fa36y2oqhao3wgg2rw1pi459 FOREIGN KEY (user_id) REFERENCES public.users(id);
 O   ALTER TABLE ONLY public.addresses DROP CONSTRAINT fk1fa36y2oqhao3wgg2rw1pi459;
       public       api    false    175    1957    179            �           2606    21962    fk97mxvrajhkq19dmvboprimeg1 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT fk97mxvrajhkq19dmvboprimeg1 FOREIGN KEY (user_id) REFERENCES public.users(id);
 K   ALTER TABLE ONLY public.roles DROP CONSTRAINT fk97mxvrajhkq19dmvboprimeg1;
       public       api    false    179    1957    177            �           2606    21957    fkmg6d77tgqfen7n1g763nvsqe3 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.phones
    ADD CONSTRAINT fkmg6d77tgqfen7n1g763nvsqe3 FOREIGN KEY (user_id) REFERENCES public.users(id);
 L   ALTER TABLE ONLY public.phones DROP CONSTRAINT fkmg6d77tgqfen7n1g763nvsqe3;
       public       api    false    1957    176    179               
   x������ � �         
   x������ � �         
   x������ � �         
   x������ � �     