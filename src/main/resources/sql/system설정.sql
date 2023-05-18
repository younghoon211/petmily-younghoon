-- Petmily 스키마
create user petmily identified by petmily;
grant connect, resource, CREATE synonym to petmily;

-- system 권한 부여
grant create session to petmily;
grant select any table to petmily;
grant insert any table to petmily;
grant update any table to petmily;
grant delete any table to petmily;


-- ddl 작성 후
-- (public) synonym 생성
CREATE OR REPLACE public synonym member FOR petmily.member;
CREATE OR REPLACE public synonym board FOR petmily.board;
CREATE OR REPLACE public synonym ABANDONEDANIMAL FOR petmily.ABANDONEDANIMAL;
CREATE OR REPLACE public synonym ADMINREPLY FOR petmily.ADMINREPLY;
CREATE OR REPLACE public synonym ADOPT FOR petmily.ADOPT;
CREATE OR REPLACE public synonym BOARDREPLY FOR petmily.BOARDREPLY;
CREATE OR REPLACE public synonym DONATION FOR petmily.DONATION;
CREATE OR REPLACE public synonym FINDANIMALBOARD FOR petmily.FINDANIMALBOARD;
CREATE OR REPLACE public synonym LOOKANIMALBOARD FOR petmily.LOOKANIMALBOARD;
CREATE OR REPLACE public synonym PET FOR petmily.PET;
CREATE OR REPLACE public synonym SHELTER FOR petmily.SHELTER;
CREATE OR REPLACE public synonym TEMPPET FOR petmily.TEMPPET;
CREATE OR REPLACE public synonym VOLUNTEERAPPLY FOR petmily.VOLUNTEERAPPLY;