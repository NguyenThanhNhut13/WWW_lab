document.addEventListener("DOMContentLoaded", function() {
    const addSkillBtn = document.getElementById('addSkill');
    const newSkillForm = document.getElementById('newSkillForm');
    const saveNewSkillBtn = document.getElementById('saveNewSkill');
    const cancelNewSkillBtn = document.getElementById('cancelNewSkill');
    const skillsContainer = document.getElementById('skillsContainer');
    const skillOptionsTemplate = document.getElementById('skillOptionsTemplate');
    const skillGroupTemplate = document.getElementById('skillGroupTemplate').innerHTML;

    let skillIndex = 0; // Sử dụng biến này để quản lý chỉ số kỹ năng

    // Thêm kỹ năng đầu tiên
    function addSkillGroup(skillId = '', skillName = '', skillLevel = '', skillType = '', description = '', moreInfos = '') {
        const newSkillGroup = document.createElement('div');
        newSkillGroup.innerHTML = skillGroupTemplate.replace(/INDEX/g, skillIndex);

        // Cập nhật giá trị của các phần tử trong skill group mới
        const skillSelect = newSkillGroup.querySelector('select.skill-select');
        const skillNameInput = newSkillGroup.querySelector('input[name="jobSkills[' + skillIndex + '].skill.skillName"]');
        const levelSelect = newSkillGroup.querySelector('select[name="jobSkills[' + skillIndex + '].skillLevel"]');
        const infoInput = newSkillGroup.querySelector('input[name="jobSkills[' + skillIndex + '].moreInfos"]');
        const typeSelect = newSkillGroup.querySelector('select[name="jobSkills[' + skillIndex + '].skill.type"]');
        const descriptionInput = newSkillGroup.querySelector('input[name="jobSkills[' + skillIndex + '].skill.description"]');
        const removeButton = newSkillGroup.querySelector('.remove-skill-btn');

        // Thêm tùy chọn cho select skill
        const newOption = document.createElement('option');
        newOption.value = skillId || '';
        newOption.textContent = skillName || 'Select Skill';
        newOption.selected = true;
        skillSelect.appendChild(newOption);

        console.log(skillType);

        if (skillName) {
            skillNameInput.value = skillName;
        }

        // Thêm các lựa chọn cho level
        if (skillLevel) {
            levelSelect.value = skillLevel;
        }

        // Thêm các lựa chọn cho type
        if (skillType) {
            typeSelect.value = skillType;
        }

        if (moreInfos) {
            infoInput.value = moreInfos;
        }

        if (description) {
            descriptionInput.value = description;
        }

        // Thêm sự kiện xóa
        removeButton.addEventListener('click', function() {
            skillsContainer.removeChild(newSkillGroup);
            updateSkillIndexes();
        });

        skillsContainer.appendChild(newSkillGroup);
        skillIndex++;
    }

    // Hiện form thêm skill mới
    addSkillBtn.addEventListener('click', function() {
        newSkillForm.classList.remove('d-none');
    });

    // Lưu kỹ năng mới
    saveNewSkillBtn.addEventListener('click', function() {
        const newSkillName = document.getElementById('newSkillName').value;
        const newSkillCategory = document.getElementById('newSkillCategory').value;
        const newSkillDescription = document.getElementById('newSkillDescription').value;

        if (!newSkillName || !newSkillCategory) {
            alert('Please fill in all fields!');
            return;
        }

        // Thêm kỹ năng mới vào danh sách kỹ năng
        addSkillGroup('', newSkillName, '', newSkillCategory, newSkillDescription);

        // Cập nhật các lựa chọn cho các kỹ năng có sẵn
        const newOption = document.createElement('option');
        newOption.value = `${newSkillName}`;
        newOption.textContent = newSkillName;
        skillOptionsTemplate.appendChild(newOption);


        // Đóng form thêm kỹ năng
        newSkillForm.classList.add('d-none');
        document.getElementById('newSkillName').value = '';
        document.getElementById('newSkillCategory').value = 'UNSPECIFIED';
        document.getElementById('newSkillDescription').value = '';
    });

    // Cập nhật lại chỉ số của các nhóm kỹ năng khi xóa
    function updateSkillIndexes() {
        const skillGroups = skillsContainer.querySelectorAll('.skill-group');
        skillGroups.forEach((group, index) => {
            const skillSelect = group.querySelector('select.skill-select');
            const skillNameInput = group.querySelector('input');
            const levelSelect = group.querySelector('select:nth-of-type(2)');
            const infoInput = group.querySelector('input:nth-of-type(2)');
            const typeSelect = group.querySelector('select:nth-of-type(3)');
            const descriptionInput = group.querySelector('input:nth-of-type(3)');


            // Cập nhật lại các trường name với chỉ số mới
            if (skillSelect) skillSelect.name = `jobSkills[${index}].skill.id`;
            if (levelSelect) levelSelect.name = `jobSkills[${index}].skillLevel`;
            if (infoInput) infoInput.name = `jobSkills[${index}].moreInfos`;
            if (typeSelect) typeSelect.name = `jobSkills[${index}].skill.skillType`;
            if (descriptionInput) descriptionInput.name = `jobSkills[${index}].skill.description`;
            if (skillNameInput) skillNameInput.name = `jobSkills[${index}].skill.skillName`;
        });
    }

    // Cancel button
    cancelNewSkillBtn.addEventListener('click', function() {
        newSkillForm.classList.add('d-none');
    });

    // Khởi tạo một số kỹ năng mặc định khi trang được tải
    addSkillGroup();

});
