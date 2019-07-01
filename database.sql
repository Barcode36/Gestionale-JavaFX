PGDMP     3    3                w           Gioielleria    11.3    11.3 4    I           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            J           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            K           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            L           1262    16400    Gioielleria    DATABASE     �   CREATE DATABASE "Gioielleria" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Italian_Italy.1252' LC_CTYPE = 'Italian_Italy.1252';
    DROP DATABASE "Gioielleria";
             postgres    false            M           0    0    DATABASE "Gioielleria"    COMMENT     R   COMMENT ON DATABASE "Gioielleria" IS 'Database che contiene i dati dei gioielli';
                  postgres    false    2892            �            1259    17230    anello    TABLE     �   CREATE TABLE public.anello (
    idprodotto integer NOT NULL,
    raggio double precision NOT NULL,
    pietra boolean NOT NULL
);
    DROP TABLE public.anello;
       public         postgres    false            �            1259    17238 	   bracciale    TABLE     �   CREATE TABLE public.bracciale (
    idprodotto integer NOT NULL,
    lunghezza double precision NOT NULL,
    spessore double precision NOT NULL,
    larghezza double precision NOT NULL
);
    DROP TABLE public.bracciale;
       public         postgres    false            �            1259    16985    cliente    TABLE     �   CREATE TABLE public.cliente (
    idcliente integer NOT NULL,
    nome character varying NOT NULL,
    cognome character varying NOT NULL,
    numerotelefono character varying
);
    DROP TABLE public.cliente;
       public         postgres    false            �            1259    16983    cliente_idcliente_seq    SEQUENCE     �   CREATE SEQUENCE public.cliente_idcliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.cliente_idcliente_seq;
       public       postgres    false    197            N           0    0    cliente_idcliente_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.cliente_idcliente_seq OWNED BY public.cliente.idcliente;
            public       postgres    false    196            �            1259    25716    collana    TABLE     �   CREATE TABLE public.collana (
    idprodotto integer NOT NULL,
    lunghezza double precision NOT NULL,
    spessore double precision NOT NULL,
    ciondolo boolean NOT NULL
);
    DROP TABLE public.collana;
       public         postgres    false            �            1259    17393    fattura    TABLE     �   CREATE TABLE public.fattura (
    dataemissione character varying NOT NULL,
    importo double precision NOT NULL,
    idordine integer NOT NULL,
    idcliente integer NOT NULL,
    nomecliente character varying NOT NULL
);
    DROP TABLE public.fattura;
       public         postgres    false            �            1259    17427    immagini    TABLE     �   CREATE TABLE public.immagini (
    idimmagine integer NOT NULL,
    immagine bytea NOT NULL,
    idprodotto integer NOT NULL
);
    DROP TABLE public.immagini;
       public         postgres    false            �            1259    17425    immagini_idimmagine_seq    SEQUENCE     �   CREATE SEQUENCE public.immagini_idimmagine_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.immagini_idimmagine_seq;
       public       postgres    false    206            O           0    0    immagini_idimmagine_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.immagini_idimmagine_seq OWNED BY public.immagini.idimmagine;
            public       postgres    false    205            �            1259    17312    ordine    TABLE       CREATE TABLE public.ordine (
    idordine integer NOT NULL,
    idprodotto integer,
    dataemissione character varying NOT NULL,
    datascadenza character varying NOT NULL,
    tipologia character varying NOT NULL,
    idcliente integer NOT NULL,
    descrizione text
);
    DROP TABLE public.ordine;
       public         postgres    false            �            1259    17310    ordine_idordine_seq    SEQUENCE     �   CREATE SEQUENCE public.ordine_idordine_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.ordine_idordine_seq;
       public       postgres    false    203            P           0    0    ordine_idordine_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.ordine_idordine_seq OWNED BY public.ordine.idordine;
            public       postgres    false    202            �            1259    25703 	   orecchino    TABLE     �   CREATE TABLE public.orecchino (
    idprodotto integer NOT NULL,
    spessore double precision NOT NULL,
    altezza double precision NOT NULL,
    tipologiaorecchino character varying NOT NULL
);
    DROP TABLE public.orecchino;
       public         postgres    false            �            1259    17220    prodotto    TABLE     x  CREATE TABLE public.prodotto (
    idprodotto integer NOT NULL,
    prezzo double precision NOT NULL,
    peso double precision NOT NULL,
    materiale character varying NOT NULL,
    genere character varying NOT NULL,
    venduto boolean DEFAULT false NOT NULL,
    nomegioiello character varying NOT NULL,
    nometabella character varying NOT NULL,
    descrizione text
);
    DROP TABLE public.prodotto;
       public         postgres    false            �            1259    17218    prodotto_idprodotto_seq    SEQUENCE     �   CREATE SEQUENCE public.prodotto_idprodotto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.prodotto_idprodotto_seq;
       public       postgres    false    199            Q           0    0    prodotto_idprodotto_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.prodotto_idprodotto_seq OWNED BY public.prodotto.idprodotto;
            public       postgres    false    198            �
           2604    16988    cliente idcliente    DEFAULT     v   ALTER TABLE ONLY public.cliente ALTER COLUMN idcliente SET DEFAULT nextval('public.cliente_idcliente_seq'::regclass);
 @   ALTER TABLE public.cliente ALTER COLUMN idcliente DROP DEFAULT;
       public       postgres    false    196    197    197            �
           2604    17430    immagini idimmagine    DEFAULT     z   ALTER TABLE ONLY public.immagini ALTER COLUMN idimmagine SET DEFAULT nextval('public.immagini_idimmagine_seq'::regclass);
 B   ALTER TABLE public.immagini ALTER COLUMN idimmagine DROP DEFAULT;
       public       postgres    false    206    205    206            �
           2604    17315    ordine idordine    DEFAULT     r   ALTER TABLE ONLY public.ordine ALTER COLUMN idordine SET DEFAULT nextval('public.ordine_idordine_seq'::regclass);
 >   ALTER TABLE public.ordine ALTER COLUMN idordine DROP DEFAULT;
       public       postgres    false    202    203    203            �
           2604    17223    prodotto idprodotto    DEFAULT     z   ALTER TABLE ONLY public.prodotto ALTER COLUMN idprodotto SET DEFAULT nextval('public.prodotto_idprodotto_seq'::regclass);
 B   ALTER TABLE public.prodotto ALTER COLUMN idprodotto DROP DEFAULT;
       public       postgres    false    198    199    199            >          0    17230    anello 
   TABLE DATA               <   COPY public.anello (idprodotto, raggio, pietra) FROM stdin;
    public       postgres    false    200   <       ?          0    17238 	   bracciale 
   TABLE DATA               O   COPY public.bracciale (idprodotto, lunghezza, spessore, larghezza) FROM stdin;
    public       postgres    false    201   <<       ;          0    16985    cliente 
   TABLE DATA               K   COPY public.cliente (idcliente, nome, cognome, numerotelefono) FROM stdin;
    public       postgres    false    197   Y<       F          0    25716    collana 
   TABLE DATA               L   COPY public.collana (idprodotto, lunghezza, spessore, ciondolo) FROM stdin;
    public       postgres    false    208   �<       B          0    17393    fattura 
   TABLE DATA               [   COPY public.fattura (dataemissione, importo, idordine, idcliente, nomecliente) FROM stdin;
    public       postgres    false    204   =       D          0    17427    immagini 
   TABLE DATA               D   COPY public.immagini (idimmagine, immagine, idprodotto) FROM stdin;
    public       postgres    false    206   �=       A          0    17312    ordine 
   TABLE DATA               v   COPY public.ordine (idordine, idprodotto, dataemissione, datascadenza, tipologia, idcliente, descrizione) FROM stdin;
    public       postgres    false    203   ��       E          0    25703 	   orecchino 
   TABLE DATA               V   COPY public.orecchino (idprodotto, spessore, altezza, tipologiaorecchino) FROM stdin;
    public       postgres    false    207   ��       =          0    17220    prodotto 
   TABLE DATA               �   COPY public.prodotto (idprodotto, prezzo, peso, materiale, genere, venduto, nomegioiello, nometabella, descrizione) FROM stdin;
    public       postgres    false    199   ح       R           0    0    cliente_idcliente_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.cliente_idcliente_seq', 577, true);
            public       postgres    false    196            S           0    0    immagini_idimmagine_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.immagini_idimmagine_seq', 63, true);
            public       postgres    false    205            T           0    0    ordine_idordine_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.ordine_idordine_seq', 25, true);
            public       postgres    false    202            U           0    0    prodotto_idprodotto_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.prodotto_idprodotto_seq', 1344, true);
            public       postgres    false    198            �
           2606    16993    cliente cliente_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public         postgres    false    197            �
           2606    25720    collana collana_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.collana
    ADD CONSTRAINT collana_pkey PRIMARY KEY (idprodotto);
 >   ALTER TABLE ONLY public.collana DROP CONSTRAINT collana_pkey;
       public         postgres    false    208            �
           2606    17400    fattura fattura_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.fattura
    ADD CONSTRAINT fattura_pkey PRIMARY KEY (idordine);
 >   ALTER TABLE ONLY public.fattura DROP CONSTRAINT fattura_pkey;
       public         postgres    false    204            �
           2606    17320    ordine ordine_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.ordine
    ADD CONSTRAINT ordine_pkey PRIMARY KEY (idordine);
 <   ALTER TABLE ONLY public.ordine DROP CONSTRAINT ordine_pkey;
       public         postgres    false    203            �
           2606    25710    orecchino orecchino_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.orecchino
    ADD CONSTRAINT orecchino_pkey PRIMARY KEY (idprodotto);
 B   ALTER TABLE ONLY public.orecchino DROP CONSTRAINT orecchino_pkey;
       public         postgres    false    207            �
           2606    17229    prodotto prodotto_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.prodotto
    ADD CONSTRAINT prodotto_pkey PRIMARY KEY (idprodotto);
 @   ALTER TABLE ONLY public.prodotto DROP CONSTRAINT prodotto_pkey;
       public         postgres    false    199            �
           2606    17233    anello anello_idprodotto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.anello
    ADD CONSTRAINT anello_idprodotto_fkey FOREIGN KEY (idprodotto) REFERENCES public.prodotto(idprodotto);
 G   ALTER TABLE ONLY public.anello DROP CONSTRAINT anello_idprodotto_fkey;
       public       postgres    false    199    200    2737            �
           2606    17241 #   bracciale bracciale_idprodotto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.bracciale
    ADD CONSTRAINT bracciale_idprodotto_fkey FOREIGN KEY (idprodotto) REFERENCES public.prodotto(idprodotto);
 M   ALTER TABLE ONLY public.bracciale DROP CONSTRAINT bracciale_idprodotto_fkey;
       public       postgres    false    199    2737    201            �
           2606    25721    collana collana_idprodotto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.collana
    ADD CONSTRAINT collana_idprodotto_fkey FOREIGN KEY (idprodotto) REFERENCES public.prodotto(idprodotto);
 I   ALTER TABLE ONLY public.collana DROP CONSTRAINT collana_idprodotto_fkey;
       public       postgres    false    199    208    2737            �
           2606    17434 !   immagini immagini_idprodotto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.immagini
    ADD CONSTRAINT immagini_idprodotto_fkey FOREIGN KEY (idprodotto) REFERENCES public.prodotto(idprodotto);
 K   ALTER TABLE ONLY public.immagini DROP CONSTRAINT immagini_idprodotto_fkey;
       public       postgres    false    199    206    2737            �
           2606    17326    ordine ordine_idcliente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.ordine
    ADD CONSTRAINT ordine_idcliente_fkey FOREIGN KEY (idcliente) REFERENCES public.cliente(idcliente);
 F   ALTER TABLE ONLY public.ordine DROP CONSTRAINT ordine_idcliente_fkey;
       public       postgres    false    197    203    2735            �
           2606    17321    ordine ordine_idprodotto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.ordine
    ADD CONSTRAINT ordine_idprodotto_fkey FOREIGN KEY (idprodotto) REFERENCES public.prodotto(idprodotto);
 G   ALTER TABLE ONLY public.ordine DROP CONSTRAINT ordine_idprodotto_fkey;
       public       postgres    false    203    2737    199            �
           2606    25711 #   orecchino orecchino_idprodotto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.orecchino
    ADD CONSTRAINT orecchino_idprodotto_fkey FOREIGN KEY (idprodotto) REFERENCES public.prodotto(idprodotto);
 M   ALTER TABLE ONLY public.orecchino DROP CONSTRAINT orecchino_idprodotto_fkey;
       public       postgres    false    2737    199    207            >      x�3461�42�,����� dk      ?      x������ � �      ;   s   x�]�9
�@�::L����s��1xi|,���O%^������Zϟ5�M�ͦF*7�͸9��[�ܒ��[�V�J���m�-7������Qv��܊���qwZ�D� |	le      F      x�3461�42��=... [�      B   �   x�e�A
�0�����@e2M�.�B�� nB$P3%I��f���h+�W��j�X ��˙E�%���Ld�$�)��<�/XG�?p�D�%Az�$�g��#r�N������p�/}��W}b�n���M�V��R���4&      D      x�ĽY�,7�e���
Cї�AemP�5'�2�!Ed�L����~�́]��˒�������������<��#��s�����������'��?O9����1��+���؟�J*��'����˩�F~�������y���q��3��YBI5�'>����_���C�zv��)�<��_����T~����qj|��ƚ?�^�O������������6����ǟ_����'���������'�����?��_?�}2w�{���9������<�������ϟ�5�߃����3W��[����ם����?���^d�cyr�3�"I+���y�������R���e~�|>'����~*�]��������{��㳖���?���o�����?��o�;9EvJJ��/�ʿ�{O��+���>���V�����{��g����������+'���{}���s�������2Ͽ}�8����vh�ԧ�#��e�Q,�3�m㨿x�/��G��;��������g�}����C^뾮�>���߹��K�5W^�!߿�&���޿�������3��?+�������P�
��0�{����m���߉�#f7��y��g�O�|j��{���^�y����ߧ�������TQ��'��yU�����������_��)T2������_��'�{������wx>��1� <�1G�#G�K���)��Q�0�o\9�+�X#���v^�6����>q�������}�˟���wy������~������=�wǓ'��}n,I	���
��ē��C�5��!��.�N�IX�/������?y����|q����U�갾Q����e�'�YF���N���^{=�[�{��R_�9e`��0Im��m�]K�j:��o��5�����<[*�(����7�Ѿ�ZX��FZ3�9����:�/���r/����7�D����7r8��{O��y:�#��۱��;�sp� (����1��[<oY3���=������l�9��w���dhymk���w�V�+�ѱ6_���}�z�LU�8��uަ��U~ben�s��oV���瘕��y��&���-r޻��S�X[R��9�40�{��|�}̶��IxY��o{j�Fjb�5��!!}�����v�l1�=�8�Mo�=���`Q�7�����U�
e��v�cs�Ӆ`,���=�����Ki��������H՚e��}�6���w�q����g��:7*�~{�2���8�W�ת�!�\�.(j��{��g=__���~����x�[�[�NM�Y���N�	B�_�~y��yKE�9�ZF6B�BŹ"��셿�>T����wԡߴ����o�~0�ő�y��ލ��SG���8��w��֋�C+�-��#�7�o��=��-r�oވ~�SQN~!r c�\����(�D�/rF|���_8�gb?����x�2��3:�3��]9l�`�i�<�$^12�(�;�h'2>�8&�1aG1������޶�I��\{êc��{��atA7�,$����չ΅@cKz�'"���OĶƩL��HM�3�|Cڜ�U�~�����&��*7���r����Й���o�V��8��A\��BŸ��˗�>�8"-J����gVǇQ9ǣB��7�b�r��U��gR@���}\2zS
�q<��7�;�}��+��D~��gܻcV^I;��?����'���+�h-_G���<�$��y�/� i�h>b���Z�~�8e�zs� ��YLX��,�*�?�S��\���I�������B�o�)����a��G�K� u�%XLf9���zd Nz�c[����������� !�}�U�'����ɼu�T�a�zm���M ��������K��Bk��{��n���x!*@�1a���Ɲc-���:F�VF	zZD���N� ���|���Ly��ȡ4uk�
�/��Z��<p[�Ԕ͌h  K,���'�x���`D��õ�WX��}�� t_��9�:b}k��<g{5�;��ïV�1�b"n���S����{N��(�hG�j����f�h����g����;>��8a�/���Fbº>;�A��v���S֛�wm�aǘ��w�\�ȁ�ݱ"�`�zi����������%�Q�f����a�Dč|q�W:��su�+v[Ww��*�	�JO_�����Ή󭰇��\����[=�&��
�3 �q�%|(1���=j�V�o7E�|ם�n��������+3�y�\�A@�y��6,;��FDYP D�h�4�ᡦ
 ��G|?�;b���0hI�+`P:n
�9F�a4���R�B��!�+X��sp�(h� U��Om;������Ppm ����������Y���q7�Ӏ�!#7�f�pjJ<�<����&�f�^Q[Lvaς���� 0/�8
Q����ƭ�3�g�g<�q�}� 0�r�?q�X:lݓ�_�p�ݘ@�	uG�+�o�9o�XO�r�Rm�!�ƨ�������J�����z�4��}�	#|Ɠ���#�wa`_�S�������/�P����2�덁���#��R�b8�>�'��m��^���# �6�3A
"��f@N�Y-h#h�= �3���� g�>�H���x��9 [�J��Z�>�:����[�@���Hc�f:6	�&_��R��0.<uҟ�mO��`��O3� ;|&�q�qA/���G�#�ǉCŠ�[x���\�Ỉ�=��|��Ńظ�H���76f��5j XR�u�Z� 	�반|��=x��Kx��? Q �P� ����������v*��p��TW>`���P3
�1���'�j`Y4@�q_��(U�p�ᬄ���p"2�-A����,�+��<
�za=��AL/��%q��U��|&FQ���i< 81m��$Q��P��b�&o�X{H��'P
���� _r��!�;��	_�����}�eᄀ[8o����A���Q��4Fa�؀27��V4|�v�(E�m�u�?��� ��%
�0���x
��eׇa.@o�D��=�&]�X�x�	���7��Ag�&�	�Û/��7�t���P|�Wyό�6�
HUW�y^#.Y�`ȇ7�������?en؍��
5'X�/���$m�ZxFd ;�>�ƅU<�)\rL `
_p���_�$x��|H������
\e4�aH�� 4/�ܫ<��t#%k�͙�;�銯@�C"0�[7V�
p.�7}�}���d��|���炉,�H8�n�����p�]� C��� oY:���5��m��ɋx���xD�/���"��aJm�;[h�!���D���!}TAjp'�
����D��J�WXL�������o?/�yu��Ĳ? ^����X����Q��Љ,�|֘����lZ��l"�`?��u��;�����}�D ���{������"^�p$��vNF#��Ŭ�V|�,9,>� ��@����B{ħ<��!I>�ץ&_�+N?
�%�z�at�緣�ߥ��[~$D)�4��5T��yE_1# �Q��Q��+�A�_f�v.��|8ښ��	���ud4�=I9�)��"��?p��7���c���iv�oK/<'����8X��޼x��6/��,�5�.���x�9����oy�]
��
��ׯ�
��G ���������� !�>��z����!&�@O�̀=��u?8�����lݨ
v�Jk&��[8b�P@�x$��t��K\Y�p
�4&0���N o���aD�mQ�q| ĔkAB�-'t2`�a:|E���j:O�x^�� �����'LU�Ƙ�%a[�����r��«��i�Ux�c��!	����S:�%��`v;
pwT mխDp��|y��$,�3�[���2���fx�@>�	N8�GB��c���y�&�/����    ���_��؂#m�-�ΠO	��� �u�����Ï8�=8�7��cXA�S�v�_��y �!�
���?�nݍߌOk;ш��w�$�0Vp�X�v��QG;�e���ፕ"j�щ�W��� �g]۵_qϼ/�ɬ�������	>g|%���3a�@�%��`J}�q��@o��3�B0�p��,��K�'��U�$`��A-���4R4��ם |������0{><vi 4"����A��ϸϻ��ؿO�qH\ϸ��
��)̎�F��U|��� R��Y4�ɸǗ�K�k�=��DI����r��2b�{�Ν�\��qdT��[2���g~�;�=0`�]��h`�ρj>�v�Q�al����ĕ�PW�nQLؕLƁ�"! �dl~���e$x����1�]O���HcB�S�[5��p�Ȅ��I��Q\�RwF�ã0(;z��{��|�� ��)\LL	�7��d/�jH6�7����E�bs��l�A^��	�˝�VS8�WK>"��߫���!��Nl�Q���X����E�^��g��m��9� V�!yz5���S��]��Q�������̋��x`T�����؈U�cа�ٸ��eHr����q�8�O����~f�M�	Z�'�����č���.6 sx0�IKy5:��
i]P�������6n�5��pH��"��!C��t[��#V/�|#�끙<��e�;�q�k%<�Bx����p�O�:�h��y��XQ!<'�ӏXT��D�����Ia̓o@6�@4t*�~L�u��������!y�/˗�6��GDa���ƥ�2�`T�' o)D�+�������kƛA6.4`�^���(�7 �5�GFf18���<'@u�:��sUȗ�CX��6`7����/F����ΰ���|h2]��Q4������t�3�%Vn
���4|i�wp<���_���C�@@��� å*0�?8qݪ�o���7�#^ˏ��%`_p'�=���_���Y��1 �#��@���p��ɡ�2KDF�ޖO��ΰ��	�U_�Z�9��r�ln��΄^[��C�.j�F(h2�����i9o��L�J��GF�l��y��;7�����`���7!3-)_���i������K'�����0��4��_��`;^�1���aM�����k��(���AG�9����,-N�+P����c�Z�j�����N�	��ph�I�ч�^�6���O0���!N3�5v��$�<" ��Y�g+h>�j�k�㉵Ǵ`���:�:͗�).��-�CSOw���#�)<2�T9�,4��&�o���A�;*��庰��T.0~������s�J�cH������Bu�s:5a�wF��%�O-�brrՈ톨�ެ��[5�J����{�m�J�-	��p��[tV+�*�D�`�����Ò0���ZB�&��C�a����Q�R��@���7�a@��& B�+E��y��(NF=��f`�8 ��X�8A������7~#Oy7�5pJK���v^�0���͙�wů~1!m��3ۻڶ�q�b��Te�]�I���2�L�����C݋[���
����{�oČu��B�q���D�p��{�:��Ue���1Oܔ��k��#����y �R0���@]�m�HӀq�NG�c����f��D�4N?>��*&�>��0$����X}�|y�h�c
W��(���c��� �iBj}� �`h��c��"N#
��<F�l?�3�>�aN O �>��Dx������`���3�gax�7� ����맠��	2�A�śV�ⵦ<<Q8�	:�˹8���fO3:�Uz�
�<7��(�-ýt\�Ƿ�qđg)�� ��4Sޒ��3�Z�% �Rs�@d���/O�1K�&8B&oY.vpH&�*�Ԋ	B͸`�Me5��Y������Ϫ <�g�
7#�޽��Y���ir��z�W{7�+O��̇Ϸ意"�T5���׀K0�wo,�@N
�+�.�@�`�]֓� (�G~�+��1�=n�j�+S���ڕȟ"�BZ����o��X����+oP�q���磾X�G��^kyrm����a���1��9G�:N�1ܛ/s�4�8馟����T�B�	/(@3���1n����7�Q"�����S���v��-�����p�� �0�.�K \u� BJc�G?����PS�*T�zn0�ï�	���eJ`��=��fQ�遥L�t��!��}�mŨ1^j�-~�^�]F��-��AI;��zC��Lb�Y���6V��<�iL�5�Y>g8�_�3.fe֞�ܖ5d�:8F���-��xw����0\^���:�πc|j5�Щ�A��n��n=����+X�cB���VD!�H�O7����Zͷ~O/!T�IL,�ܖ)��ʹ�]�d�}�}?|Z��*�����H��_l�2R0d�k��wy)�����J1��0ǭ[m�r]6���Ƨ�����HK���vml��[��d�	A�`D91����o���Q"�Ih��F��� f�-��!xT�3��Pu8�	�o[��2s��f:6
�Y�$�y��3x}A\�n8�kk<m~��Ny���h�/PJ����p���\>[&\ΏR��wV��y�$x��-h���8��I���
�^2��t�>¾y?���t������!�o�V�i���Z_h�����������f�=~�(M��2��1�$>7_�M3�q@��ՙ��!(��<��Y;�6�;k�rbj�	F�����~�e܍��O�Bǔ�>���E�֟c(��劅ߊ'���W�P$#m^͂��{��3�l d�\�Xl{3����#���<�0�G�cpU�z�a��ذ�7��6i��N�0u���q�'�T}!��-CW�N�E�*Dn����pL&
��������h�X�6j�=`<��K�����n@K�:,�IH&��G���{����G2�����1�lT��e_�DD �0=*//[��w��+�C���� 4�h^�w������a4�
��m'j��"�3�����_{��ߥj��$~�*�j�ł�܀F� ���Y ���^���w����GI�� _K�6����p1T/m<.�B�P��-����ǒ�}�nB_`��D���`�p����ډI�1��zP[�;�wyϼ�ې8@��-3�H�7޷@�r��v��0`.c�m`M`A��s`\#@�6����,/1�ҍ�����x�4DY�8-�l���dZ|{�S��/L6Gҍ�I}��Ǽt�ˣ�ڿ�U�}߂��|a5|�)D ���:f{\2,W��^���9�@�����̻4+�2�[Y�47��n(�L��Ƃ�{Ƹ�)� Fۅ�j�R�q��x�f�0�'Ê�+9�a�I#�{�d���BT�z�o�Ӏ&�[�����n����@�x�Lϳ`�7�(ִ��sL��Hr`"�߶D�e@�n��H���q����+��b�~8���m1eͨx�y�ϭf��߫�w��fK0n�=�1E��*� ��%��$����SЕ�^~�p7�8`��g�X�1@(���Bk?����=q�(�@�n��Z�g����A�?���s�=����b�l�J�`-
�D��|�ʩ901`^�O3?]=�\���������An�D+X� �}���qF�W��"�s�{��W����VS�W���o��ܴ��(r5���Z���Oc��b, VB;-������.b�dAz1Z�~@�J$�+��I�p�k�6>�ڏ�wÓ	���o��m�a$
!�qa_C-��(�Ö,AZ'Yfht�^|ˉf�1�YoD*��h<�׻�J��9$�iC:��d��XDp7U�D0(�[��f     ���\�Xq��0}�A+�n��g�~z7���O.Nx������'�av���No�����q��9�ȹ 3�ы�I�G��R�X�	B��*���7��̿rH�*(�47�Т ���LHa}{@�ث`߇�FZMA,DH ��=Z燐Z.e���P�(;�E�����m�8ևM>"jK@0�f�{XC`ݿ�I|r�a���y�S���#r�qoK��-ďㄖm<�X��+(��]�q��	��@-�����7��Y/o@�/��O�ʯF�� �;��7�_% �2�e!lU����:���a�[�iC����F?R}�aJ<�n��Q����
�6��|��LT��n�s��x,ƾh��_@;��	�/�, �����('���YH����s��b��Wb����8��he��U��U�XOϑC$1���!<�C���&�/���;V�Ӵ��5XwC�<�zS*�Vf���,�JdK{xF����Q%4��F��a�Xxb<hOϔxK���0I�X9l�K��F��W�ρ+n�5���_�~Yk��X򠑶��Ri_�X4��I�X��B�'��!h��^i��5�k��ZRn^�`��h��q:�j�]y�E]�VCd�~Z������䵝����7��D�͚U�U�!�G��=�42��*GCm
�,���"�E ��5�«ɳR�l�~��9��_���h�}�1v��ը�kl�U0:u�]8d+�)/h��`eg2N�����{�㫪=�&����;��D�q*AĻ,/\�L�?p�6ט�A������,ɘpuw��6U����>�C�X��5�G�(N1�[���������à�l����(��@J���cª01�ܦu�f��D۰DpT�R?�뭠�څ�uǆQ��	{5g��ڢ���E��l���l�u����$C��A5|	rv�*�#��m�i�?�9�y7�n�T4�bz��p�h	W
OF�>4�v�`m�x��S��c�'	�Pp��2c'�{���22 �1!�����@r�1��?^�B��ۏ���a=nR��U��5�٪|n�j���4h-UA����T���.>�j�%�R����w	�e�:EN�[��"�<n˥%����~ݾk�.��ߞ��?˛%�F� 3z�2��@80/s��L�M�ǧ���т� ZV��f@�l�Y���������*��^�ۛP8�7AD��n��j�(�Z������� ��_[� 0FbmZ�t���,X��������,)�m2P�&��ᖾ�noд�햂|��:L*<�V?���Z��+���(�ő�T ^	�<�xR��Ϫ| Z��l�mK`��4%�#�!ua��J<��Y���P�v����V{[z�@6�a��ɼ�n�"�Slw�%[o|�CN �x�f����/��D(sG�pӈ�獓c�12�	�7,-f�릛�z�̼e]��g�����~���	���B��^&���|�^�!'.��9��o�k<�>�Ѕ�"�k���*}l��8p~_kd+v�Zn��⃖.��1yc��-�>�l��"hVȿxYU� |@�}�mV���9�?���r}i݊d�˞���|?�"C4��-����E��׌�f�x��ɛ��{F/��Vv�wb���ifMЎ_DmY������Ɣ�����O�}�)���(o9X��E"��}Bq
�zhB�m �՟����x깱3��2.����[��3M�7��Y16@��&��t!$4������2js�:رbf�Zr�n���l�+��͕�h�F�ULtm*̔��W��+��W��v�@�^�W�-���Dk�������޴�6"X�5�v�>�__W�8%�?4* ���h�裍wկ�'�P����O;�e���.#�[/�jo�i�ly�U�]������Nl-��[�l�W��Vܨ��;s;xk��NS�x7�lY*t�I�a'Z�4ҭ�Em�k��^x��$m:&41P�:�����an���a`��m���)j2Nm�����
h��j�ޠ��۷:����6�,�����[h	#��b��*����vq�����bn�u�n�!hkZ���w��ַA�X]*N��K��>������_���	��'�"����XЋ$  ;O]�m�z0��D;�^�"�0S�|6�Nk|-Gn`l�����&�ǲG���2"~X�nO�(#���0Oi!��Jˣ�����5��/�.\򱰫| #�f�Sǚ�ߨ��z0�`g��Q�} �f��Յ����<o4�g7�� �(խB���@Dl6�Y�s
��^`W�\0(\���UP�1R�~7M��`!ɠ�e���ߚ˞�#b��Y��%p>��(��Гѭ����C%��huI�^7���(���w��l�����0[7t.$� �$p�v�����U�Վs��w� ��� Z���ڴ�K��l0��8�M�,��Fvt;���)D�x
�7/��Q9��߅?Hq�:4�d'���XmV��?��<���X3t��I�ڻ��x��l?�q�_r��}�@�;`�S,���`��MX	�7�� �k���jf���pl�����o���?_�ނo4�H&���%�H�]�v�"�0K!�:6~lJ��#{�Zlf����ڸ�bOwL��� )?�z~����X��2��l� WX�~@�N�ϰ[)t#�|�5���,�n�1�ԭa�d.^����_�֢�7`��r�i�����xJ �-O*22\�v� �	���������k#��X	w�Ќ`<F��)r��BW�Ok<Wf��?c��n�a!��Cf?/�� �^���*��Dϭ���Vq-_t�dٶ�X`M� j9��d��.C���P,��?Қ�H��Ok3�O�M�	s�f9�B ����-0A�ql�cevx��7V�Z�
 �4 �Mgㅶe��"r�ه�Sp("d[7�Yc�
R,�w�ĔG̻0X�*w�K�@{,Ͷ�񙆼�c�:7UC3�l5)�h@��j�<��]O\��6;����8@F`u�6��R���jV wS|������ |4>��"�>��C1�c�<4ܨ�z�)�1'*X��m{ː:@(d�
����l�]��!�@�![�6���_,���N��3��9e ��L��p�t�@��K�������:�8�7Ėp��^�[�v
��^bQ2��Z $�t.Ŷ�E����W�r�hguӝ!V�¼�Y�awvǠ���'�"/�؀C$x:��n�rW��F/*6G���3�u\�c�*��VJۨ$"�Y�3{��З��������qV�m�v�Nw�pǜ� ����V5Gˡ�끵�0�o�`�x���^�I�n@D���z�n��5��NT����1L|Q42t��7��Mq�|aq��r�b_��s��M�ۍj%�����x/*6M�q����o\7g�c#;��a�%j����lK@o"�9��z�%��bj;������WeA�v ;�#��h��b�w�?���c ����E�2@�	����l��j��1V;,J}︠s����=0�Q�� ����4����~�&~՘�3�%v{���m�����H����Um�����Tt��^���~���g�?��!Ns����߱H��q`�N�k���*R6�o�a���̰4� �p����ׅ㊂:GH���`*�[����
'&����>vJ=�7��]�o�,��,f������MY���9�ǲ��6�����Ř����d���(%���� �/ ��e^��UA�Xq�{���=���D.�,����c[ڃzs�xz�yK����1 ע��tN/������E�!��xs��sk�,^c����֯��q�XSnc *6ÿ��ж%G�7#o�/gG"4��[RkSh6<Y��e+��M�����-큸>�9�*���Z����S��>�ʂ}�����U��v<�S7�_�,��;�')f�LnaDz[�Ȏb
MXd    �8�Ü~ۂk��MM��v�
��bx��T�"b��s���m��7�`P���7۱`�pRXFP�qj�D�z������
�To��acl�!��a�/��� �u������*��O;i_�����\K�bWw�EɰG�`��f��f�f�� ��i/(��[u���>�@�1b�x ɞ-h8�1�9�0r���G��nI����b�pks�f)�uۢap~�o�$��vTc�;h���T4aD<W����|�}����N����ׇh����(䞩�=2N �6�[�r�e��k;8_��sA��z�����#�����d^��`#:�go���؊��`3, C�-ӳ�aP���+/� iL��q{:����v Y63L7:��|S�Ҧ;���,���c~P�KC^�Uwv��8��9!�aH�ĻSlNX��C��]��p��B���=}K�v����9̖U�ꊷݮݦ��X�1��B�v@��`�k����z����5;�r>1�2h��$��E��q(T2
kQE�<��R�lDQ�s��ͷC�z��O�8x�v��cG���vA�}�*�B}��A��eۮ����i���v���i���7�ڀ�I� �K��W�'l���a�F`�>v�qu�Z�	5ڼ9����;���\�B� o߈˸��]�^���u8&I��گ�8���⮎�u�M��y��$�\�6�e�;���{�7�(�	~����8{0�S>�l��%�&�!c-�u�s��K��tWk��jk�kh���:`�:���� G�l�Í4��!34�[�k�Je�� '�3��k��Øɦ�9d
����I-��lC�1��\��0��:��o;�6g��/��
�7�^��e�����+'`��	����#��iH����ZJ��q0�d�X"����i�eX& BQQA�}Ox�#�p�<�&����� ���o[�!4�:��"NY�LL6F~}�Z>k��0,*8sH��`/T�6#��Ѿ���]k�`[�	:/��4�7����⬿;1��.Τ��kn�4oό]����Tq6�a�ƅ?;fl�:p��5��r��N�lZ�d�	p�p�i���LJ؇�;^���N�ɯ���XXn�w	����nrX�]l��>:w���(�Dف��d��ŻF�����!X���3�rI =������y0��б��v��]y�aa���|�C�pa�F�	�<&�*��Ln�zƴ�k��I��,�F�3��FZަzL���n�|��^0�Wg.�Йt�V3Z�;>G�9׀#ȻY�T�H��W��B�[*�*;����Rg\������>+�1�8�������z�_��c<��B�BE��-Vͪ�a-gÕ?��A�@>>��u*V�����_�ғ.���Z� ���yo��� 7ok���I�Z��[|�:@1h���`�*�gy����b�9���#?�44����ŝ�v��
H;�w�_w��׎1��7�w@]P�skJ3)�Z|,����_�Hr&� � T�ũ*��GF������=r�;�������nS�ݘ�B�;Y�^x ��0�:0[U��%�=�f����1:�1	w4�c�#�B���>8<��W+Q��9���{��>T���(���r��ؚ��]X���=�9t��w���o/�N+Q�~3�G�b{��o�1N��n>�޾g�;��)�w~6OW��&Iy|p����1��_6S�֝#e�ۂ�����w�xÈ����ɉc�@�����`�e{�㱝&�*營/���4��3�rAK�����}�,�y����@�q �ܬd�G�Y3����J[�{ؠJ#r*Y�1�L�ݒ�Ka��m9sH�{!���d��O '��_gc=��e���9�$<�HQ^��#{�u�؜c�Fu8Md�7��rr��H��ƕ�[OQ���I��5X}p��%��]f��Ӿ����|�[2�r�z�܄��4��k�N�V�#pJGp�����������˛w��v����r�| �GS�Q�<���*�~8c�N�C՝@����c��ڋ �AYm0;�����0���k������vAױ�Rv�簛����X��f[L�s[3���:`�FnL��9�Br�L��F������;ΕGm�a��eB0(�56c ���̆〵��؛�߬�p>�w�#}��q��k����>��jø-�A�u\tx�5�ﭿ����[�\��\.�sN'�Պ3G��)�?{��� ;m[�:�b�>�va�|9�T����
�Ap��m*����dT�B�:mD<\����:�Z��]CC�I��q^���/�I��Ճ8���N��x�2�� o���a�nL�F@�Z&n2�(XJ
�nS-\1Xޝ�*�i�h8�jH����$''܌4l���c�aޜ���J����tC3fR~Ss��ۼ�\A�e��Y���g%���p�ˀJ*�l�9���ȡ�|�g�Pq<���Gu�r�|:l
j3���$οzB�^��YAm�}�	�aKՖ Ț�g�s�
��C��-v6T<���V������A5�V���	<��J�b�%�@��@`��g.��;v���ո�WK0�H-��J�3i�&o�m0]삄`�hu��1T������	�����;�~-y�uX�1�Ρs����=S��Y-����uň�F��H�7�� [�^'\�;4�����-��<�@�?Ɯ����k����C,�?ˁ8���hh�q6��L@���[��x�c 8�;l8Ȟ�^��h8�r��ޟ-�����£�b���#v��4`��]�E��F#G�j��:cK��[N|�����f����|瓏f��s@x�a59-\�q#�1_�e�{_�*U8�TQ��B���K�x1���P��vz �X���{Mg/��}�p��u!�!t�W�p �#�_�vC#�N=q� ��;�v��8e�G
ٮos���vԅ׾㧀����ǿ~)�OO�+��X�j7Н�ҟ;��u ��â�l��ZHI�8�	Xi��3��̀����X�n*��,{A��|M�!�����o�>&�Ĥl��Y��?� ���1`��C��`���f1+���S�D��^�q��=�ᷡ~��i�o(pC|&�3�Lǩ|�"g�o�Y+���n֟4�oQ�.���"�e(��:=-{�M�#��=����^���Q����q��U�kk�r+������t�k���&x��n?Dm�����k#�%7-�Qێ�4ڦ��u���:������x3����d��s��o6��/�waj�:k�I��_����fi(
����0c��b���v ���j�9���`ʆ���W ��>�M����5��W��K"l���I_H�Ȧ���XwZ��DޝC�X$��u{��u��0@>��1x|=1*�����T8�����+�ޔDT �װ �ڜ�
n��ZS��Nn)���1��Z���&���kL�q�cݡO�!��י�(����;Ւ��7]�a�f^ٖ5���Z晜�y�u5��^�0��x���	qA�g��(s�Er����]+�҅(y�(�Ng	#�Ζ�{9��;CK!�� F����u#9�^��6=Wܫ#��zm��s6�r���t:��Jc��>G��l�<��@m�w��x����0�����%�.鰦ItЦ[�܂�Wr��}T��������V!�M�C�d��rV�wc�.r��i1ݺ,�zڢ0^�Q���=���[�\9pα��8oJS����t�=ܗ�z���i��f��Y���E� � �6�3��~:�1�Iv�q�Kz0"~�#Bt�6�=��t���jr�b�}����&x�xI�T5K�k��������ƒ� lp0��Ȼ�EZ�%��@J'�m�.��\�f�Ԇ�	%�4 �pG˘��Mu�f����� ��� ����;���y�Y�v�Az��nA|�9�@p�>�e���r(�c���3�gx��/����m��#���WW3E�ͦ�">V��;��Rs��4s��|��s�^E���    �6B��� �n��ɦ�՛w`tR�?0�]��r����+ ��Z��c8���N�^���bE�C/P��Z=P�Σ��n	z�Zc����*ǹ`]l��gV��mS��V_��&f�/4���L2��L۽$|N�C�n�ԙ7<��c1��fC}.wB�r+�_�-��79�X�n�9��[M	��0�t��/���D�f����s��Bp��ڥ�ljv���A��<G���g[�\V���3Y��w�uUg�F`M�����ؘuЇ�h�r^�C,�$��bx1������m����\F�ڝV}��q�|��'����Wb���v��-|��k0�!��ɚ��Ato>�{��?����_��l�OmF�X���4�M�;�s-'9ukռgF�bϵ������o����LK��04�"�8�t�'�*�g�Ph)�ş��",;q�g0�P�7�y$;��Y��(Ni�Y��a�8���o�[�Ad�Y��3�g��Ow�>|�G�-�7���F��À�ga7�����:�6w�8�fY_h���
��w��ó,�N[�.;0"��$!7�uB8�a�Ё� ��Ϳv':9��H8�7�v�Ԑ�3��|��d(J��5,������a�6m�Iޝ�ǚN� ��=`����o7Q1Nc��3O��W�v;�K�y�'Nyz�H&�;�$�h��`�nrÀ���=�L�"�u�V�m3Oɴ&�ZN��v���1�v�`�Vv75M�o7��+�l��<��b��x\{L�(�'})V�����N��<N�C��Վ����뇁�S�o����Np�s�S�;���IFr����2i|�+�3�����3��BZ:�cu��y��0ʖ3L�5�`�h�����N�btp��W��� �0��� �(�=�ˤ8��v ��w���5n��p��U�@���Wƶ�[��;`�ǀot�D?�n�����Xv���P\	`�'���[�Y@���q�� <W��].F97���.9�BC@�A��p���F��V���a���L��pY��Nʃ탽o��M|�iG�kϋ�����Vv4|6�n'��E\�Q�n��AA��M�	t� �VD�yk��p!��p9o�,��nYYnuB����=y\��Z��r�98�}��sV2�S�y�?g��w6�U���-3s�W���n�����ـl޲{.E��D�m��?`
a������ �����+΍�����c��t֙��.�������6�����,�t P{��٢+�d�8�6t796�P��u�*;�=c]�oX�Əipu�5��z8"�ŵ ��?�K�p*�C�Kj6�X�,�ͱYj�Kw�M������`q��5�a���_���[�z��L6L0��괙
��M�PX_���i�7�O[}B5�U;�bj��;7=�^j��9z׉�`r���������=�R)�@�^��.Q�f �f�>�p�E�>6�]�TmS�l���c��-��kL�:2��f�kE�RD��į8�����Ea�F!���>�i��`(�nB����j	z=��o{���+� ��/7}�����F�W��Br���&�@�3�Z֝Æ���V�ә�q̋i��*+�yu�ވ��+��-pw�-�m��;��haZ������~ޏ�����`�y{�;8p*ر2Ia�X�����s��7�v���T;� �ؔ_ݞ=ն,T���SK喯Ф��z�#�/nO�}�T������Δ�b�j���j�Us/�l:E�f��4�s�z�x@?�`�q1
V �8"����|]�$��tZql�k;͋1��m˭��X�Wi%r�ΧoFOu��\�#�o��ۚڭ��u�&�A(F�s�����|6�����(�esY�Z\�l	�v�U��rBǚ|
���v7��������� �T��������pt��;%�������7������ �v\.*���9Dߍ\�N`G~��1���;���%%�T�z��`F��橜�pl��.o��(Kj6;�⠎����{,��n�x�+�h�*;ؚ�@0H�6!�~\�r|��>F�qf��ts�p��:�iX��/�=��GN���/�+b%�	;�a���L�[�0}���xL�*�@�6��M~����q��F"X~Y�l̕��U7�Q^���%Sν(��9�!��aX���ޡ�<���7%��D�!ǒx��E �I��cT�Z~Xa`:��Z��L	&�sa�Yn0�a�;�׶ �4�2�!�����TR�bpC*w��4p���N^��2���Յv��J��n��Мi_���^��N��ϵ·3��s��B�<��Ƞ�㈷�P�g`���:����E�n`�J��#$����^�-!:H��p�O��H���ǈ���M�`x��g�Q}����*��^'�l�p�U��w��H�ݞ��nD� 'ynń>��}��ϫ�!s����������鋰\��m6n�/��YwiРہ
�� WP�?��Kz~���E��|�)�.�.tԢi�;�(��ĂM+��= q�*g�"��<�kX�-E�8��n�"N���� ���Ŵ��r*,I<��d3�VV��ZQ��v&��;�5G/88�Ĝ�t,�q���]�m��) &�e5g(�����\�|�Z����yx����`����,8o�d�=�����Q��H��nC��+�1�FZ���{��E>K*��jZs�W{DO.������F'l �+��n�(V!g�k�k�8���w���	��cw��6Z��cЁ�p��9F�l��/�4��A�������P���8P�ࠇn���k��7F	gw)��wFk\��鷸�����9@Һ�d�G=�i���goSi�����ߑg�aF�,F��V|V���J^��4��v��t�[��*�W���i"��O*���ft���.2�g�����d�j^�6߽��~C��wt��c��oZ�q�>�ƚAG�-�0R��N�u?_��v��N`��Uq]:Q\Փ~��oNoq]���r���١�#x��b��r�!���!.����u��Fw�?�@A�SU{RCp=or8䍳T��0�Ȓ{؀I���`���RAv`��i�Γ���t��	!�-֊��� ~ӎoaM9^� ��K�䖡.�>"�j���5?�J�� G܈g�Ak���x�� ke�������d�q� Pw�]/���F[�˱6w@ LP����}7��{�V���>@�4��e���������K��B]Z��q���ĹU�ڜoi̎�0�����Wo���FPl��]?Ww������)t:'pmV�f�l`��P�an�^G,�;C��o��ܷ#؞U���Ce�aX���sr�e����uv���q�V�=N���X�ң|�iW�4���$����(��������{�xWb�Ư�2����D��< ����\q����w�h_̓m�������K�[�a�X��^��ws�z��<�8���N�L.zN�v�Fs]U����l��c� %�Hh��
��m���)�,���Bk�V�^8�%o��'31hTw�������$$3�]:�3�ܗ�:G�������M6d\7ߞn�(���vwl�����3i��c�7fg[:�Ea"�j��5Se�Ln6�o�������Ӱ�-E\��0��m�OF����:Jv�\��o��V|�Fz�EB����O��9�7��i	�����
���xl����ՁrL#H���[�u>Nv�S�#���Xg�9�9i��x�n�C�����]�]�t8���Y��n�� �̿ƣ�z�y�'�9�)8��=���f�/M��?Ɓ����c�q���Kţ͖�á|���E�s��I��|.��ə�l��\.�rt�"�n?�?��Qu�ۡ���L�Rmj8���gG�ae��h��s���\ס��V!�����w�C��ɚ��Nv��Ծ���pqZ�`��n`���23�kgڭ҃&Z�d)+����Mm��!4�~��hM��nܭŝx�)�N�|>"Y�    T�;O�3�����aRT��eU�Jy�"���Ş|e��4�o��r�J��W¬��.9��5��޼�Դ�cZ�s��%K��X;A���Z�'`�i��d+R#��:Ƈ�'9�����X�3�Zn���;Y��r!��H��"��,sw��q�Ct$�5�.|�,_����C��>4'��[>�'z�L[g;K��m��iu�{I:���[.9�rI����:�ǌ!�.|�����Lò\z<9'�v��[����og{)�k�X(��v�qI�W���]ܴ�A؅`�ݠo�栄��u�t���VFb�'��G�O�r���-�6!���Z����M���`��o�c�mU��&3�Ef����s(��`����%D�S��u�.x�U�����M92d�tY�u[f?��liv+"e����}js�Rnk9�~uK��̵�>ݦ]�<N��44�ɡ�ɾw��ew3ړ,u��u1��H�Z��B�^���0��CcSM�<,���t��������p�7�����N�C�q��J'Z������v�0���t��vz-�:�Np��qE��C�dҰ�7��)��]Vhl�m|a�FލH��`���&��0��z�vS���k�-�v�{z^�<wtβ��Z8KX�s�/F��6�7sM'�o�����{�9�2�f�z����i߁!Û��a�4�3�j�+&
���a�9w�گ$Zt��M��@�}�N��B�.�Iw����SB�Ǧt�]���4#o��)�Uq��.p>A�_Xs�y-�:ތ-`���8F�1����]�{Jm�t�?8`m}� ���^���d�b�n�M#�oL�ñ�>��G���UH���;/�`�vX�tw:V�!��03_c���1����6&�����x$�=ou��}j�ݛ�þ:��.���=�iW�o�S���:���ʼ�)ǝ�3� ���Fc��g���Zmk[�Gf
�0�Ư�����`�,����K7�d�"����v���;V���nႰ��8�'��e��`�9��B���O�}?r�}�d[�͋R�cr8ݑ��~�uB~ۮ&s��=.�*��� ���t7�?ڿ�-������
'�	�Q����ۢO��0҉+��.����J��22l��wԻHN��N|C��+�zeW$s�OtjƊF�M��.<��E��� �'���ؘ;�u&�M�H�n�0���^J�m	�Z�ݓb����B�"̺8��IN���!��5���U;��ks�.w�|��>�"XA/6]ݥ0�.L*V�f[���u �c>����N�r Ium���%a?X��	<��Ԫ��B@�±e���?Fy�
��D
�R~?;<�;(�N�B��%��;���zL>p+�̏�
��;��ch�J�	����8�_wn�����o&?|�����Y�=�
�=T�������L0���$d@Q�붚[P��jA1�8:Wk����?�rK�	�u���/�: ����� ;�L����N/k��L�e��� �!�8�͆d�@E����X��qâ�����u��F�d'�:����;�,ύ�>Aznͺ	��{P���R�X>�<�h���Qq9�n�+�G��٫z�&������R�>�,���U�[�r��(�Bpb3>7����W�
�=w���9C�ٌ�����2e�0�)��&�tw{���`��H,����u��-�<�J&gK�4�'�����y֟���S��qt�*�r��8o�Qr��d$"  ���v�PL��pa�y��]��\�fZ���>KX�N7(��d���0��:� aA�s�n���#�+�AU �	;)��m��?�<	������N�V�>�S1];��I���D�ݱ)N�|��U{\*�iߕ��Y
���`���i�'�{�&d���w>�����N���	���q��£����I',�k�4 �Inz:�+8I�Lr����Iw�����-c�H��)^]/ՂK}-ڙ"��ph��7�R��&"8a�D�ܯ��8���홶)�E9�Q0�rƭ�v3�>n��H�h�:S��>��f,�)͜�
���<��6T��t���Oڝ�h33���Ky���R�8	{�����_�qoqԞA~���-�j~a[��Up�s�(焛q�������k��=`F�T�^���jU��M�:U9�Y�F0�m���Q� 7G�ȏ��欗��1kv�4n��P�����(��pU b���u`�_��:�f��@��|����*=�'Y���jBl����#��#�Ͷ�v�FoS�oԦ�}>����s�lrw!�o��6�}�U���Q5ɪ4cנWK��V�����j�	��L���.�w�:���=c��1��p���H������l]�=�lűξ�{��:5��`�3����`�C��ޒ�Y}n�rT��ͥ�;~:eJ��0'�jx��T�W|l2����vR :�$Δ�mb�Ə�N�;��n'U��x�N�q�\�O�@{��~7�r�E���c�@x� ��?M�q�O6�紦lA��m-����^w�ɡ@��i*��"�<��vT(��6?�6�����8�X�$��khʥc�����(-L�⮾��l��r�]t։���`8L�+�_��ͦ[�mw��"yL�5�Kͯ ]X;������,����"���~���M�9����gR��aj	��u��\c�p�� ��<��y3�����;��!�[jC�_�S�A/n�׭B\�3��w�D1١s����_8�<v�
 Ib1�X�=nT��(NOt�Z���5�����$�$8eLk�Ȉ ��K�;��2�/�i�����xt#�Xa�'�N2W"�����SO����X��p��A�v��.�[/�;�x��րU�dN$ �����-vT�_W��u[�]����a�b�}��~�������xA"\�웆j�S\P1sw.+�y$��d��H$m1�.K�k��>�f׾��XN�y/Gr�TƘ�E��a�/�����@�v��Q|KÂx��;�J���)&ΡB:��?�g�N�0Ǽ�J7+i&����vg���8&��y;"���������v���R��l7:�[Sz03{�L�<It���鸏��M��7�$X�)���(w-�-@���8]�w`�x�Q7.�+C9 `��M�L�7Y�l��`#|�~Ɵ>�YKo��C6���d#�hIT���q�儇�;�UZ�����)�����u��SE3��a�����,�s�d�ٯ����g��Y}"�����:����<�L�1�
�~zc[X�Z_+2�ܒ��J����x�&��c_�Ý΅�]�`�����b>�Uq���qu(���c�n��w)�]aд Go�}(K�,kα�۸�׏�������7��"�� ����ҕ�~����6٭��N�����%Ir��Z}Y���h���� �2mF==U�d� �m`

��oyEz,�tS�})��XS%&>c�*�i>:�N瀷�D��i���̶$gIn�l�s_K�I{��I~��	ǥ$k�i�o	��v(#�m�1*�$IT�r������ќ�+����i�f�&M=�����6s,,���<<=��&���m��Ξ�K(��eX�Hŧ%��H��-�6�>����Z�⽤v�9����� <��e�����eid����8�?wq"��a��1�*��׆��`�jL��كxM[)q��hJ�?�R��6VNI�9	�����[�����|��q�B��G7Cr�J�����v<o
9�d�M.�s�����5������i�DM��N[Jx<����EC��ro�E��{� (I/Kx�.KJ\�ȧ��Z�?�AC�_h�O�.�rkp��%_m۫ú����`=�#h�﫳~?�Ӕʊ�!�)a����{�`��_vY�3��
��P�Fe8xjU�����0@	��j�b�$�/>�&��jнF�ۭ}�Z%v'��5V4mo�kn�o� �  ���~�2VJ����vg�����h�ʐ��O3"�	0���O����)p'm{��i�r��޹'�$�IS/bƍu���4�$v���r�k��g$�i2NO�@�!MGbp�;���'b'>_��+�����R�g}��B����|����-����Ԃ�����,�tԏ�}^�'泚f�=h�I$��Iw�俱��P���;�� �GՌl��R�� ��i����u���󼉒������ˍ�V��5��1�������kJ�lֿY��������'1*a7�=Lut������%�� �Tb��X�O�w�fD�w����G*�����ٍҥ`�`��u"�����K[A��[����vTIE����?�i���7#��l/��]���m}����~�f���%6{Z1�M�՟����b#�g�^�g�^�˄�@��(J��6�9�)��m/���4����y�lB����\����7t�\��QHe{����[ш�0��$7v�;���j�C���R�Ċ�O���xTs�n���@�b{��%�����C�5���$o��$r��nJ8w��U��}���T� ����<)�i��]jk9���N�CDo)�ؕۑ�?�H�����{��BvR��-V�]���2�Ͱ���O�=4e�ᑗj?�`��-ht�9"���v�ү���sBU*��i�T���X�9X��~�E
��t>�5H��[/Q-��	*M��ג���_�?O~(͛��БK��r,�+�>��'�5A�U��V4����©.��s�\��q6ޗ
0���j�<2�48=��m��7*�㶯w�<�f�}�`�4�5�U�-������UVR_�0�cn�v��2+�BRM��f�y���l��)�g��|����G�k]G�!��V��PDF��'�� �:�G��ٍ̻��M�R�m��-���5�]6�iU�E�=l.N	l���UL���g>m+h7eI1�?����Q����>zOAZ9��88�<瘨�p[=�o�,�w���ed6�{)��̠� ���C'G��vZ���>j�����ܕ��kD�^�#�i���ߙA'amV�L�c���+	4Ww�IZ_	K4��d]���ЭP��T�֠�<�|�S	�����?�Z�	�<0�ES��D��Z�:(����W�[f�G�A��bg�!F�"몹L¾nJ S�J�Dr�՞*����+/�2�*^.��^�aƕTk��`�n$M���3��i�eM����09�4�h��j'�+C�<�����T `��nztnu ����@���v .�b�B��-��wV$H˭��<�Ȼ�a�گYz)c�� ��k��=�Æ��o���d̃�:�^�@z�i�����@�Ug�ۙ�m
�Z��R��'�l�I�R�}��M��XWZ�I\���:�Z�̯+�f֐l��:Fzr��_�i|9����)Z���c�Sj��,r\N�A�wA>�Q:7��ֿ���$^��&�`�`9� o�y$�ܪ��\`��IW�L�2��#�����nN#Cz��>�!�s%$�}�nL���[e��2��t�K�gHo�t��3�?Q#)�>��h,��8lϷ���i����N��+o�ԩ��`�7�cÀ_KY�ll@od����q����ĲfP�_��<0��#��t�[�;Af�׊zp�?y����-9�r����`S��a�UY�E�?q�>yi�'m��D��K+Ay�j.[巤g��a��H@�9�˸3�Q*�{gr��p�I8+�N�s�/�$������ύ !��@�l�#]��K�#�U�Ig��ORPx��J�D
���0B�&n���e���=�d�����8���5O�:Ō�zk�	{����kB��5�ć�N�Sp��R=#�L0ǠպK�����q�hL	&�3֥+�����SJv��٨�,��}��E�%�痦�Ć�HT���c��
��\V�"Yq�w���i�&�j��#�$�����Ġ��oC���s��%ů�5�ORi7�	�s%�x������K�
���>�Y�W�[���<]]z(z�jkWh�1-Y��rNʋ$H^^	�N�/׀�
Lq�;�"���pW\��z�@2qʏ��?��O#g^V�蹓�rq7�H����j�Dv5?Bj�?��}�
l׃?��R{�By�KP�^I>����Ќ?�;S_�k�y)��:O>�}�`K�ӛ|�4�1�ԴE���"F3/�v�֨�Î���n
M��/��ӈ傴��M��E>�͝2Ur  ����� ��zeC�^iޗ;H
������s��I)�R�`"�J^v-�$&.<��j���ӾSF��͹��ͮ|��YI��g��c���b�;���4z@L���>ZV��{j�;=�<�~�s���\�a���Eg�?�(�CX��YC��������8op��r��,AY�$ۇ�jP���Jt���7C�%hyb�sZ�o�i9^懔�R��dS�+�`�@�8����$z�H��'������5�L��;9�S�[ pY�x��7��0�r�O�-Śհ�Q�jZ�'���D���E���������鋬�n�9���f�5�r)�+�����끲�Ipܦ�5�|{������Af�p�5��ښ,��^Q3�ց��;��K��bMy̻,W!�*��#�]:�:���Hu�ھhX��5��7�;R}�b��� [��Xa�K�T�(�4�|��;J�\n%�n������%l�8y�$�b��jV\(^>��.��q�LR����Q�jh�D.���dޫt�N����<������F�o�dg��	w�-7���(6k-�ں���06V\��Gq)������mꝺ�GY{=W�hx�.<_݀Jj�º�h��������!V��f���[h�������V�c�8á0o�4br�R�y�c���jO��l���9�I�z�߰���9PjaU�+y,�Fw10d�~rd��0�	�}���� PZ��͎�ܶ8t�Ȑ�b(��NK/�[��B?c���2��JQ� �A��2*мp�8G`ÁG���.�΀�F��6�?W`,�!�`Fl��F��..��l{iE`[���Q��S���qSL�fU4ϾM�6y���dF��(�y��L��*�ޝ*{�7�	:K���O��r�weG����=�w�rm�C�%��wX&cw�[׃��Pk���������&�t�|`�����d��	m��z��>�4AH� �uS�u�xS	d�+䔦���d��g�"��o㱀��q�f�[�>��C�_T�b�	
���so�5�`���� g܎=��͛b��[�꠯�x��O���B�9zI��|��i�\�B��o �HnP��.���:���/hH.���J�p�M�}kO���n�4$���m�%8�踋D]����xB.jv'Z���UY��z�B >	�lS��'A&Z|E
����֦ͥ���=܃����8C]��3�<��z 	7���?�6�u@���o^ʑ|������m)}���%w�i��x��,��|m1�H!��s�.���J<-��3�ѷ���U���,�|��ee���Y'�Z��'O�$��*1S�嗸���ֲM��{&, 4��D\�>���ɟ��-[Ah�P�ݾ�d�}}pB�&���3i4�o���%N��T�������/R�[ng˓{�>�y ���{�!���Oe��oP"�^�7�3�Z�ij���Ln
��Mnh߯��X���5v�h#)�ܥ\�"9w	���ۨ�V6K��e�p�ny��}?}K���_�KH�|�!��Xx]�5������fl@p���W:Y��j�A,?�+mU��r�s��yO�g7u��䧭cT}��mMz�"��R���<ծ	k�5U�^[>�ì��%s����l���yKMn#�TP���RCHH�@(��e.ݕНr�1óy�X�MFJ�H���Ƿ'f��c0�#��p+:���[i
�Ԝ�=�h�jȃ�S�-����������e����������B�      A      x������ � �      E      x������ � �      =   _   x�3461�42!� �x'OG?gN����̜T�4N����ļDC�d(����W�����PP�_��ehlb�id��ǼԜ�|C�D0�ń=... ��)I     