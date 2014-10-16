class Post < ActiveRecord::Base
	has_many :coments, dependent: :destroy
end
