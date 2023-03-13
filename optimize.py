import os, shutil

BASE_DIR = '/home/guido/Desktop/utn'
changes = 0
deletes = 0

class logger:
	GREEN = '\033[92m'
	YELLOW = '\033[93m'
	RED = '\033[91m'
	ENDC = '\033[0m'

	
	def red(self, msg):
		print(self.RED + msg + self.ENDC)


	def green(self, msg):
		print(self.GREEN + msg + self.ENDC)


	def yellow(self, msg):
		print(self.YELLOW + msg + self.ENDC)

logg = logger()


def folder_filter(folder):
	if os.path.isdir(folder) and folder != '.git':
		return True


def image_filter(image):
	if image.endswith('.png'):
		return True


def guide_filter(guide):
	if guide.endswith('.md'):
		return True


def list_images(folder_path):
	return os.listdir(folder_path)


def move_images(folder):
	path = f'{BASE_DIR}/{folder}'
	images_path = f'{path}/images'
	if not os.path.exists(images_path):
		os.mkdir(images_path)
		print(f"created dir: {images_path}")
	images = list(filter(image_filter, os.listdir(path)))
	for image in images:
		current_path = f'{BASE_DIR}/{folder}/{image}'
		image_path = f'{path}/images/{image}'
		shutil.move(current_path, image_path)


def format_md(folder):
	global changes
	guides = list(filter(guide_filter, os.listdir(f'{BASE_DIR}/{folder}')))
	for guide in guides:
		guide_path = f'{BASE_DIR}/{folder}/{guide}'
		with open(guide_path, 'r') as f:
			old_data = f.readlines()
		new_file = open(guide_path, 'w')
		line_index = 1
		for line in old_data:
			if line.startswith('![]') and 'images' not in line:
				old_image = line[4:27]
				if len(old_image) != 23: # harcoded
					raise Exception("warning, image length changed")
				new_line = f'![](images/{old_image}) \n'
				new_file.write(new_line)
				changes +=1
				print(f'[{folder}]: changed line at {line_index}')
			else:
				new_file.write(line)
			line_index +=1


def clean_not_used_images(folder):
	global deletes
	path = f'{BASE_DIR}/{folder}/images'
	images = list(filter(image_filter, os.listdir(path)))
	guides = list(filter(guide_filter, os.listdir(f'{BASE_DIR}/{folder}')))
	
	if not len(guides) == 0:
		data = ''
		for guide in guides:
			guide_path = f'{BASE_DIR}/{folder}/{guide}' 
			with open(guide_path, 'r') as f:
				data += str(f.readlines())
		for image in images:
			image_path = f'{path}/{image}'
			img_format = f'![](images/{image})'
			if img_format not in data:
				os.remove(image_path)
				print(f'[{folder}]: clean not used image {image}')
				deletes+=1
	

def optimize():
	global changes, deletes
	folders = filter(folder_filter, os.listdir(BASE_DIR))
	for folder in folders:
		move_images(folder)
		format_md(folder)
		clean_not_used_images(folder)

	logg.green(msg=f'TOTAL CHANGES: {changes}')
	logg.red(msg=f'TOTAL DELETES: {deletes}')


if __name__ == '__main__':
	optimize()

